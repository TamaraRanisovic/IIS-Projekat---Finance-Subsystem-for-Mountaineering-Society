package iis.iis.service;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import iis.iis.entity.Expense;
import iis.iis.entity.Income;
import iis.iis.enums.ExpenseCategory;
import iis.iis.enums.IncomeCategory;
import iis.iis.repository.ExpenseRepository;
import iis.iis.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PdfServiceFinance {

    public PdfServiceFinance() {
    }

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;


    public byte[] generatePdf() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Bilans uspeha").setFontSize(18).setBold());

        List<Income> incomes = incomeRepository.findAll();
        List<Expense> expenses = expenseRepository.findAll();

        Map<Integer, Map<IncomeCategory, Double>> incomeSummary = incomes.stream()
                .collect(Collectors.groupingBy(
                        income -> income.getDate().getYear(),
                        Collectors.groupingBy(
                                Income::getIncomeCategory,
                                Collectors.summingDouble(Income::getAmount)
                        )
                ));

        Map<Integer, Map<ExpenseCategory, Double>> expenseSummary = expenses.stream()
                .collect(Collectors.groupingBy(
                        expense -> expense.getDate().getYear(),
                        Collectors.groupingBy(
                                Expense::getExpenseCategory,
                                Collectors.summingDouble(Expense::getAmount)
                        )
                ));

        List<Integer> years = incomes.stream()
                .map(income -> income.getDate().getYear())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        Table table = new Table(UnitValue.createPercentArray(years.size() + 1)).useAllAvailableWidth();
        table.addHeaderCell("Kategorija");
        for (Integer year : years) {
            table.addHeaderCell(String.valueOf(year));
        }

        table.addCell("Ukupni prihodi").setBold();
        for (Integer year : years) {
            double totalIncomeForYear = incomeSummary.getOrDefault(year, new EnumMap<>(IncomeCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            table.addCell(String.valueOf(totalIncomeForYear));
        }

        // Add rows for Income Categories
        for (IncomeCategory category : IncomeCategory.values()) {
            table.addCell(category.name());
            for (Integer year : years) {
                double incomeForYear = incomeSummary.getOrDefault(year, new EnumMap<>(IncomeCategory.class))
                        .getOrDefault(category, 0.0);
                table.addCell(String.valueOf(incomeForYear));
            }
        }

        table.addCell("Ukupni troškovi").setBold();
        for (Integer year : years) {
            double totalExpenseForYear = expenseSummary.getOrDefault(year, new EnumMap<>(ExpenseCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            table.addCell(String.valueOf(totalExpenseForYear));
        }

        for (ExpenseCategory category : ExpenseCategory.values()) {
            table.addCell(category.name());
            for (Integer year : years) {
                double expenseForYear = expenseSummary.getOrDefault(year, new EnumMap<>(ExpenseCategory.class))
                        .getOrDefault(category, 0.0);
                table.addCell(String.valueOf(expenseForYear));
            }
        }


        table.addCell("Dobit (Ukupni prihodi - Ukupni troškovi)").setBold();
        for (Integer year : years) {
            double totalIncome = incomeSummary.getOrDefault(year, new EnumMap<>(IncomeCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            double totalExpense = expenseSummary.getOrDefault(year, new EnumMap<>(ExpenseCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            table.addCell(String.valueOf(totalIncome - totalExpense));
        }

        table.addCell("Porez na dobit (25%)").setBold();
        for (Integer year : years) {
            double totalIncome = incomeSummary.getOrDefault(year, new EnumMap<>(IncomeCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            double totalExpense = expenseSummary.getOrDefault(year, new EnumMap<>(ExpenseCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            double totalProfit = totalIncome - totalExpense;

            // Check if profit is positive before applying tax
            if (totalProfit > 0) {
                double taxOnProfit = totalProfit * 0.1; // Assuming 25% tax rate
                table.addCell(String.valueOf(taxOnProfit));
            } else {
                table.addCell("0"); // No tax on negative profit
            }
        }

        table.addCell("Neto dobit").setBold();
        for (Integer year : years) {
            double totalIncome = incomeSummary.getOrDefault(year, new EnumMap<>(IncomeCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            double totalExpense = expenseSummary.getOrDefault(year, new EnumMap<>(ExpenseCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            double totalProfit = totalIncome - totalExpense;

            double netProfit;
            if (totalProfit > 0) {
                double taxOnProfit = totalProfit * 0.1; // Assuming 25% tax rate
                netProfit = totalProfit - taxOnProfit;
            } else {
                netProfit = totalProfit; // Negative profit remains unchanged
            }
            table.addCell(String.valueOf(netProfit));
        }

        document.add(table);
        document.close();
        return baos.toByteArray();
    }














    public byte[] generatePdf2(LocalDateTime startDate, LocalDateTime endDate) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf, PageSize.A4)) {

            document.add(new Paragraph("Bilans uspeha")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER));

            // Fetch incomes and expenses within the specified date range
            List<Income> incomes = incomeRepository.findByDateBetween(startDate, endDate);
            List<Expense> expenses = expenseRepository.findByDateBetween(startDate, endDate);

            // Calculate months between start and end dates
            List<YearMonth> months = new ArrayList<>();
            YearMonth currentMonth = YearMonth.from(startDate);
            while (!currentMonth.isAfter(YearMonth.from(endDate))) {
                months.add(currentMonth);
                currentMonth = currentMonth.plusMonths(1);
            }

            // Group incomes and expenses by month and category
            Map<YearMonth, Map<IncomeCategory, Double>> incomeSummary = incomes.stream()
                    .collect(Collectors.groupingBy(
                            income -> YearMonth.from(income.getDate()),
                            Collectors.groupingBy(
                                    Income::getIncomeCategory,
                                    Collectors.summingDouble(Income::getAmount)
                            )
                    ));

            Map<YearMonth, Map<ExpenseCategory, Double>> expenseSummary = expenses.stream()
                    .collect(Collectors.groupingBy(
                            expense -> YearMonth.from(expense.getDate()),
                            Collectors.groupingBy(
                                    Expense::getExpenseCategory,
                                    Collectors.summingDouble(Expense::getAmount)
                            )
                    ));

            // Create table with dynamic column count based on months
            Table table = new Table(UnitValue.createPercentArray(months.size() + 1)).useAllAvailableWidth();

            // Add header cells for categories and months
            table.addHeaderCell("Kategorija");
            for (YearMonth month : months) {
                table.addHeaderCell(month.getMonth().toString() + " " + month.getYear());
            }

            // Add total income row
            table.addCell(new Paragraph("Ukupni prihodi").setBold());
            for (YearMonth month : months) {
                double totalIncomeForMonth = incomeSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                table.addCell(String.format("%.2f", totalIncomeForMonth));
            }

            // Add rows for Income Categories
            for (IncomeCategory category : IncomeCategory.values()) {
                table.addCell(category.name());
                for (YearMonth month : months) {
                    double incomeForMonth = incomeSummary.getOrDefault(month, Map.of())
                            .getOrDefault(category, 0.0);
                    table.addCell(String.format("%.2f", incomeForMonth));
                }
            }

            // Add total expense row
            table.addCell(new Paragraph("Ukupni troškovi").setBold());
            for (YearMonth month : months) {
                double totalExpenseForMonth = expenseSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                table.addCell(String.format("%.2f", totalExpenseForMonth));
            }

            // Add rows for Expense Categories
            for (ExpenseCategory category : ExpenseCategory.values()) {
                table.addCell(category.name());
                for (YearMonth month : months) {
                    double expenseForMonth = expenseSummary.getOrDefault(month, Map.of())
                            .getOrDefault(category, 0.0);
                    table.addCell(String.format("%.2f", expenseForMonth));
                }
            }

            // Add total profit row
            table.addCell(new Paragraph("Dobit").setBold());
            for (YearMonth month : months) {
                double totalIncome = incomeSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                double totalExpense = expenseSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                double totalProfit = totalIncome - totalExpense;
                table.addCell(String.format("%.2f", totalProfit));
            }

            // Add tax on profit row
            table.addCell(new Paragraph("Porez na dobit (25%)").setBold());
            for (YearMonth month : months) {
                double totalIncome = incomeSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                double totalExpense = expenseSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                double totalProfit = totalIncome - totalExpense;
                double taxOnProfit = totalProfit > 0 ? totalProfit * 0.25 : 0;
                table.addCell(String.format("%.2f", taxOnProfit));
            }

            // Add net profit row
            table.addCell(new Paragraph("Neto dobit").setBold());
            for (YearMonth month : months) {
                double totalIncome = incomeSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                double totalExpense = expenseSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                double totalProfit = totalIncome - totalExpense;
                double taxOnProfit = totalProfit > 0 ? totalProfit * 0.25 : 0;
                double netProfit = totalProfit - taxOnProfit;
                table.addCell(String.format("%.2f", netProfit));
            }

            // Add table to the document
            document.add(table);

            // Close document
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }




    public byte[] generatePdf3(LocalDateTime startDate, LocalDateTime endDate) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf, PageSize.A4)) {

            document.add(new Paragraph("Income Statement Summary")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER));

            // Fetch incomes and expenses within the specified date range
            List<Income> incomes = incomeRepository.findByDateBetween(startDate, endDate);
            List<Expense> expenses = expenseRepository.findByDateBetween(startDate, endDate);

            // Calculate months between start and end dates
            List<YearMonth> months = new ArrayList<>();
            YearMonth currentMonth = YearMonth.from(startDate);
            while (!currentMonth.isAfter(YearMonth.from(endDate))) {
                months.add(currentMonth);
                currentMonth = currentMonth.plusMonths(1);
            }

            // Group incomes and expenses by month and category
            Map<YearMonth, Map<IncomeCategory, Double>> incomeSummary = incomes.stream()
                    .collect(Collectors.groupingBy(
                            income -> YearMonth.from(income.getDate()),
                            Collectors.groupingBy(
                                    Income::getIncomeCategory,
                                    Collectors.summingDouble(Income::getAmount)
                            )
                    ));

            Map<YearMonth, Map<ExpenseCategory, Double>> expenseSummary = expenses.stream()
                    .collect(Collectors.groupingBy(
                            expense -> YearMonth.from(expense.getDate()),
                            Collectors.groupingBy(
                                    Expense::getExpenseCategory,
                                    Collectors.summingDouble(Expense::getAmount)
                            )
                    ));

            // Create table with dynamic column count based on months
            Table table = new Table(UnitValue.createPercentArray(months.size() + 1)).useAllAvailableWidth();

            // Add header cells for categories and months
            table.addHeaderCell("Kategorija");
            for (YearMonth month : months) {
                table.addHeaderCell(month.getMonth().toString() + " " + month.getYear());
            }

            // Add total income row
            table.addCell(new Paragraph("Ukupni prihodi").setBold());
            for (YearMonth month : months) {
                double totalIncomeForMonth = incomeSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                table.addCell(String.format("%.2f", totalIncomeForMonth));
            }

            // Add rows for Income Categories
            for (IncomeCategory category : IncomeCategory.values()) {
                table.addCell(category.name());
                for (YearMonth month : months) {
                    double incomeForMonth = incomeSummary.getOrDefault(month, Map.of())
                            .getOrDefault(category, 0.0);
                    table.addCell(String.format("%.2f", incomeForMonth));
                }
            }

            // Add total expense row
            table.addCell(new Paragraph("Ukupni troškovi").setBold());
            for (YearMonth month : months) {
                double totalExpenseForMonth = expenseSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                table.addCell(String.format("%.2f", totalExpenseForMonth));
            }

            // Add rows for Expense Categories
            for (ExpenseCategory category : ExpenseCategory.values()) {
                table.addCell(category.name());
                for (YearMonth month : months) {
                    double expenseForMonth = expenseSummary.getOrDefault(month, Map.of())
                            .getOrDefault(category, 0.0);
                    table.addCell(String.format("%.2f", expenseForMonth));
                }
            }

            // Add total profit row
            table.addCell(new Paragraph("Dobit").setBold());
            for (YearMonth month : months) {
                double totalIncome = incomeSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                double totalExpense = expenseSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                double totalProfit = totalIncome - totalExpense;
                table.addCell(String.format("%.2f", totalProfit));
            }

            // Add tax on profit row
            table.addCell(new Paragraph("Porez na dobit (25%)").setBold());
            for (YearMonth month : months) {
                double totalIncome = incomeSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                double totalExpense = expenseSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                double totalProfit = totalIncome - totalExpense;
                double taxOnProfit = totalProfit > 0 ? totalProfit * 0.25 : 0;
                table.addCell(String.format("%.2f", taxOnProfit));
            }

            // Add net profit row
            table.addCell(new Paragraph("Neto dobit").setBold());
            for (YearMonth month : months) {
                double totalIncome = incomeSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                double totalExpense = expenseSummary.getOrDefault(month, Map.of())
                        .values().stream().mapToDouble(Double::doubleValue).sum();
                double totalProfit = totalIncome - totalExpense;
                double taxOnProfit = totalProfit > 0 ? totalProfit * 0.25 : 0;
                double netProfit = totalProfit - taxOnProfit;
                table.addCell(String.format("%.2f", netProfit));
            }

            // Add table to the document
            document.add(table);

            // Close document
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }













    public byte[] generatePdf3(int startYear, int endYear) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Bilans uspeha").setFontSize(18).setBold());

        List<Income> incomes = incomeRepository.findAll();
        List<Expense> expenses = expenseRepository.findAll();

        // Filter incomes and expenses within the specified year range
        List<Integer> years = IntStream.rangeClosed(startYear, endYear)
                .boxed()
                .collect(Collectors.toList());

        Map<Integer, Map<IncomeCategory, Double>> incomeSummary = incomes.stream()
                .filter(income -> years.contains(income.getDate().getYear()))
                .collect(Collectors.groupingBy(
                        income -> income.getDate().getYear(),
                        Collectors.groupingBy(
                                Income::getIncomeCategory,
                                Collectors.summingDouble(Income::getAmount)
                        )
                ));

        Map<Integer, Map<ExpenseCategory, Double>> expenseSummary = expenses.stream()
                .filter(expense -> years.contains(expense.getDate().getYear()))
                .collect(Collectors.groupingBy(
                        expense -> expense.getDate().getYear(),
                        Collectors.groupingBy(
                                Expense::getExpenseCategory,
                                Collectors.summingDouble(Expense::getAmount)
                        )
                ));

        Table table = new Table(UnitValue.createPercentArray(years.size() + 1)).useAllAvailableWidth();
        table.addHeaderCell("Kategorija");
        for (Integer year : years) {
            table.addHeaderCell(String.valueOf(year));
        }

        table.addCell("Ukupni prihodi").setBold();
        for (Integer year : years) {
            double totalIncomeForYear = incomeSummary.getOrDefault(year, new EnumMap<>(IncomeCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            table.addCell(String.valueOf(totalIncomeForYear));
        }

        // Add rows for Income Categories
        for (IncomeCategory category : IncomeCategory.values()) {
            table.addCell(category.name());
            for (Integer year : years) {
                double incomeForYear = incomeSummary.getOrDefault(year, new EnumMap<>(IncomeCategory.class))
                        .getOrDefault(category, 0.0);
                table.addCell(String.valueOf(incomeForYear));
            }
        }

        table.addCell("Ukupni troškovi").setBold();
        for (Integer year : years) {
            double totalExpenseForYear = expenseSummary.getOrDefault(year, new EnumMap<>(ExpenseCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            table.addCell(String.valueOf(totalExpenseForYear));
        }

        for (ExpenseCategory category : ExpenseCategory.values()) {
            table.addCell(category.name());
            for (Integer year : years) {
                double expenseForYear = expenseSummary.getOrDefault(year, new EnumMap<>(ExpenseCategory.class))
                        .getOrDefault(category, 0.0);
                table.addCell(String.valueOf(expenseForYear));
            }
        }

        table.addCell("Dobit (Ukupni prihodi - Ukupni troškovi)").setBold();
        for (Integer year : years) {
            double totalIncome = incomeSummary.getOrDefault(year, new EnumMap<>(IncomeCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            double totalExpense = expenseSummary.getOrDefault(year, new EnumMap<>(ExpenseCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            table.addCell(String.valueOf(totalIncome - totalExpense));
        }

        table.addCell("Porez na dobit (25%)").setBold();
        for (Integer year : years) {
            double totalIncome = incomeSummary.getOrDefault(year, new EnumMap<>(IncomeCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            double totalExpense = expenseSummary.getOrDefault(year, new EnumMap<>(ExpenseCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            double totalProfit = totalIncome - totalExpense;

            // Check if profit is positive before applying tax
            if (totalProfit > 0) {
                double taxOnProfit = totalProfit * 0.25; // Assuming 25% tax rate
                table.addCell(String.valueOf(taxOnProfit));
            } else {
                table.addCell("0"); // No tax on negative profit
            }
        }

        table.addCell("Neto dobit").setBold();
        for (Integer year : years) {
            double totalIncome = incomeSummary.getOrDefault(year, new EnumMap<>(IncomeCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            double totalExpense = expenseSummary.getOrDefault(year, new EnumMap<>(ExpenseCategory.class))
                    .values().stream().mapToDouble(Double::doubleValue).sum();
            double totalProfit = totalIncome - totalExpense;

            double netProfit;
            if (totalProfit > 0) {
                double taxOnProfit = totalProfit * 0.25; // Assuming 25% tax rate
                netProfit = totalProfit - taxOnProfit;
            } else {
                netProfit = totalProfit; // Negative profit remains unchanged
            }
            table.addCell(String.valueOf(netProfit));
        }

        document.add(table);
        document.close();
        return baos.toByteArray();
    }




    public byte[] generateNetFlowsPdf(int startYear, int endYear) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Izvestaj o novcanim tokovima").setFontSize(18).setBold());

        List<Income> incomes = incomeRepository.findAll();
        List<Expense> expenses = expenseRepository.findAll();

        // Collect data for each year in the range
        Map<Integer, Double> totalIncomes = new HashMap<>();
        Map<Integer, Double> totalExpenses = new HashMap<>();

        for (int year = startYear; year <= endYear; year++) {
            final int currentYear = year; // This ensures that the variable used in the lambda is effectively final

            double totalIncomeForYear = incomes.stream()
                    .filter(income -> income.getDate().getYear() == currentYear)
                    .mapToDouble(Income::getAmount)
                    .sum();
            totalIncomes.put(currentYear, totalIncomeForYear);

            double totalExpenseForYear = expenses.stream()
                    .filter(expense -> expense.getDate().getYear() == currentYear)
                    .mapToDouble(Expense::getAmount)
                    .sum();
            totalExpenses.put(currentYear, totalExpenseForYear);
        }

        Table table = new Table(UnitValue.createPercentArray(endYear - startYear + 2)).useAllAvailableWidth();
        table.addHeaderCell("");
        for (int year = startYear; year <= endYear; year++) {
            table.addHeaderCell(String.valueOf(year));
        }

        // Initialize variables for Pocetno Stanje Gotovine and Gotovinski Suficit
        double startingCashBalance = 0.0;

        // Add data rows
        table.addCell("POCETNO STANJE GOTOVINE").setBold();
        for (int year = startYear; year <= endYear; year++) {
            table.addCell(String.valueOf(startingCashBalance));
            double currentYearSurplus = totalIncomes.getOrDefault(year, 0.0) - totalExpenses.getOrDefault(year, 0.0);
            startingCashBalance += currentYearSurplus;
        }

        table.addCell("GOTOVINSKI ULAZI").setBold();
        for (int year = startYear; year <= endYear; year++) {
            table.addCell(String.valueOf(totalIncomes.getOrDefault(year, 0.0)));
        }

        table.addCell("GOTOVINSKI IZLAZI").setBold();
        for (int year = startYear; year <= endYear; year++) {
            table.addCell(String.valueOf(totalExpenses.getOrDefault(year, 0.0)));
        }

        table.addCell("GOTOVINSKI SUFICIT (GOT. ULAZI - GOT IZLAZI)").setBold();
        for (int year = startYear; year <= endYear; year++) {
            double surplus = totalIncomes.getOrDefault(year, 0.0) - totalExpenses.getOrDefault(year, 0.0);
            table.addCell(String.valueOf(surplus));
        }

        document.add(table);
        document.close();
        return baos.toByteArray();
    }



}
