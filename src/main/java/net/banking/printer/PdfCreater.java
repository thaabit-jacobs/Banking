package net.banking.printer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.banking.models.Account;
import net.banking.models.Transaction;
import net.banking.models.User;
import net.banking.service.AccountService;
import net.banking.service.TransactionService;
import net.banking.service.UserService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class PdfCreater {

    private final List<Transaction> transactionList;
    private final Account account;
    private final User user;

    private String pdfPath;

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 20,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public PdfCreater(List<Transaction> transactionList, Account account, User user){
        this.transactionList = new ArrayList<>(transactionList);
        this.account = account;
        this.user = user;
    }

    public void createPdf() throws IOException, DocumentException, URISyntaxException {
        Document document = new Document();
        pdfPath = "/home/codex/Desktop/projects/banking/src/main/resources/statements/" + (user.getFirstName() + user.getLastName() + LocalDateTime.now()) + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(pdfPath));

        document.open();

        addTitlePage(document);

        PdfPTable table = new PdfPTable(4);
        addTableHeader(table);
        addRows(table);

        document.add(table);
        document.close();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Transaction Type", "Transaction Amount", "Success", "Date Created")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table) {

        transactionList.forEach(c -> {
            table.addCell(c.getTransactionType());
            table.addCell("" + c.getTransactionAmount());
            table.addCell("" + c.getIsSuccess());
            table.addCell("" + c.getDateCreated().toLocalDate());
        });
    }

    private void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);

        // Lets write a big header
        preface.add(new Paragraph("Account Number: " + account.getAccountNumber(), catFont));

        // We add one empty line
        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Balance : R" + account.getAccountBalance(), catFont));

        addEmptyLine(preface, 1);

        document.add(preface);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public String getPdfPath(){
        return pdfPath;
    }
}
