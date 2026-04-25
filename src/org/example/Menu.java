package org.example;

import org.example.domain.*;
import org.example.utils.AuthorCodeGenerator;
import org.example.utils.BookCodeGenerator;
import org.example.utils.CustomerCodeGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    Library library = new Library();

    public void menu() {
        System.out.println("Welcome to the library!");
        System.out.println("Check the options in the menu");
        System.out.println("[1] Books\n" +
                "[2] Customers\n" +
                "[3] Loans\n" +
                "[4] Authors\n" +
                "[5] Exit");

        int option = sc.nextInt();
        switch (option) {
            case 1:
                books();
                break;
            case 2:
                customers();
                break;
            case 3:
                loans();
                break;
            case 4:
                authors();
                break;
            case 5:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void books() {
        System.out.println("Books");
        System.out.println("[1] All book available\n" +
                "[2] Add new book\n" +
                "[3] Find book by title\n" +
                "[4] Find book by author\n" +
                "[5] Find book by genre\n" +
                "[6] All new books\n" +
                "[7] Back to menu");

        int option = sc.nextInt();
        switch (option) {
            case 1:
                allBookAvailable();
                menu();
                break;
            case 2:
                addNewBook();
                menu();
                break;
            case 3:
                findBookByTitle();
                menu();
                break;
            case 4:
                findBookByAuthor();
                menu();
                break;
            case 5:
                findBookByGenre();
                menu();
                break;
            case 6:
                allNewBooks();
                menu();
                break;
            case 7:
                menu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }

    }

    public void customers() {
        System.out.println("Customers");
        System.out.println("[1] Add new customer\n" +
                "[2] Find customer by name\n" +
                "[3] Find all customers\n" +
                "[4] Back to menu\n");

        int option = sc.nextInt();
        switch (option) {
            case 1:
                addNewCustomer();
                menu();
                break;
            case 2:
                findCustomerByName();
                menu();
                break;
            case 3:
                findAllCustomers();
                menu();
                break;
            case 4:
                menu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void loans() {
        System.out.println("Loans");
        System.out.println("[1] Make a new loan\n" +
                "[2] Make a return\n" +
                "[3] Find books rented by customer\n" +
                "[4] Find history by customer \n" +
                "[5] Find history by book \n" +
                "[6] Back to menu");

        int option = sc.nextInt();
        switch (option) {
            case 1:
                makeNewLoan();
                menu();
                break;
            case 2:
                makeReturn();
                menu();
                break;
            case 3:
                findBooksRentedByCustomer();
                menu();
                break;
            case 4:
                findHistoryByCustomer();
                menu();
                break;
            case 5:
                findHistoryByBook();
                menu();
                break;
            case 6:
                menu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void authors() {
        System.out.println("Authors");
        System.out.println("[1] Find all authors\n" +
                "[2] Find author by name\n" +
                "[3] Add new author\n" +
                "[4] Back to menu");

        int option = sc.nextInt();

        switch (option) {
            case 1:
                findAllAuthors();
                menu();
                break;
            case 2:
                findAuthorByName();
                menu();
                break;
            case 3:
                addNewAuthor();
                menu();
                break;
            case 4:
                menu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void findAllAuthors() {
        List<Author> authorList = library.getAllAuthors();

        if (authorList.isEmpty()) {
            System.out.println("No authors found.");
        } else {
            for (Author a : authorList) {
                System.out.println(a.toString());
            }
        }
    }

    public void findAuthorByName() {
        System.out.println("Name: ");
        String name = sc.next();
        Author author = library.getAuthorByName(name);
        if (author == null) {
            System.out.println("No author found.");
        } else {
            System.out.println(author);
        }
    }

    public void addNewAuthor() {
        System.out.println("Name: ");
        String name = sc.next();

        Author author = library.getAuthorByName(name);

        if (author == null) {
            System.out.println("Date of birth: ");
            String date = sc.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateBirth = LocalDate.parse(date, formatter);

            author = new Author();
            author.setId(AuthorCodeGenerator.getNextCode());
            author.setName(name);
            author.setDateOfBirth(dateBirth);
        }

        library.addAuthor(author);
    }

    public void findHistoryByBook() {
        System.out.println("Book title: ");
        String title = sc.next();

        List<Loan> loanList = library.getLoansByBook(title);
        if (loanList.isEmpty()) {
            System.out.println("No loans found.");
        } else {
            for (Loan l : loanList) {
                System.out.println(l.toString());
            }
        }
    }

    public void findHistoryByCustomer() {
        System.out.println("Customer name: ");
        String name = sc.next();
        List<Loan> loans = library.getLoansByCustomer(name);
        if (loans.isEmpty()) {
            System.out.println("No loans found");
        } else {
            for (Loan l : loans) {
                System.out.println(l.toString());
            }
        }
    }

    public void findBooksRentedByCustomer() {
        System.out.println("Customer name: ");
        String name = sc.next();
        library.getBooksRentedByCustomer(name);
    }

    public void makeReturn() {
        System.out.println("Code of loan: ");
        int code = sc.nextInt();

        Loan loan = library.getLoanByCode(code);
        library.makeReturn(loan);
    }

    public void makeNewLoan() {
        Book book = new Book();
        Customer customer;

        System.out.println("Do you know the book code? (yes/no)");
        String answer = sc.next();

        if (answer.equals("yes")) {
            System.out.println("Code: ");
            int code = sc.nextInt();

            book = library.getBookCode(code);

            if (book == null) {
                System.out.println("The book was not found. Please try again.");
                menu();
            }
        } else if (answer.equals("no")) {
            System.out.println("Title: ");
            String title = sc.next();

            book = library.getBookByTitle(title);

            if (book == null) {
                System.out.println("The book was not found. Please try again.");
                makeNewLoan();
            }
        } else {
            System.out.println("Invalid option. Please try again.");
            menu();
        }

        System.out.println("Customer name: ");
        String customerName = sc.next();

        customer = library.getCustomerByName(customerName);

        if (customer == null) {
            System.out.println("Date of birth: ");
            String date = sc.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateBirth = LocalDate.parse(date, formatter);

            System.out.println("Email: ");
            String email = sc.next();

            customer = new Customer();
            customer.setId(CustomerCodeGenerator.getNextCode());
            customer.setName(customerName);
            customer.setEmail(email);
            customer.setDateOfBirth(dateBirth);

            library.addCustomer(customer);
        }

        library.addLoan(library.makeLoan(book, customer));
    }

    public void findAllCustomers() {
        List<Customer> customers = library.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer c : customers) {
                System.out.println(c.toString());
            }
        }
    }

    public void findCustomerByName() {
        System.out.println("Name: ");
        String name = sc.next();

        Customer customer = library.getCustomerByName(name);
        if (customer == null) {
            System.out.println("No customer found.");
        } else {
            customer.toString();
        }
    }

    public void addNewCustomer() {
        Customer customer;

        System.out.println("Name: ");
        String name = sc.next();

        if (library.getCustomerByName(name) != null) {
            customer = library.getCustomerByName(name);
        } else {
            System.out.println("Date of birth: ");
            String date = sc.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateBirth = LocalDate.parse(date, formatter);

            System.out.println("Email: ");
            String email = sc.next();

            customer = new Customer();
            customer.setId(CustomerCodeGenerator.getNextCode());
            customer.setName(name);
            customer.setDateOfBirth(dateBirth);
            customer.setEmail(email);
        }

        library.addCustomer(customer);
        System.out.println("Customer was successfully registered.");
    }

    public void allNewBooks() {
        List<Book> bookList = library.getAllNewBooks();
        if (bookList.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book b : bookList) {
                System.out.println(b.toString());
            }
        }
    }

    public void findBookByGenre() {
        System.out.println("Genre: ");
        String genre = sc.next();
        List<Book> bookList = library.getBookByGenre(String.valueOf(Genre.valueOf(genre)));
        if (bookList.isEmpty()) {
            System.out.println("No books found of this genre.");
        } else {
            for (Book b : bookList) {
                System.out.println(b.toString());
            }
        }
    }

    public void findBookByAuthor() {
        System.out.println("Author: ");
        String author = sc.next();
        List<Book> bookList = library.getBookByAuthor(author);
        if (bookList.isEmpty()) {
            System.out.println("No books found by this author.");
        } else {
            for (Book b : bookList) {
                System.out.println(b.toString());
            }
        }
    }

    public void findBookByTitle() {
        System.out.println("Title: ");
        String title = sc.next();
        Book book = library.getBookByTitle(title);
        if (book == null) {
            System.out.println("No books found.");
        } else {
            System.out.println(book);
        }
    }

    public void allBookAvailable() {
        library.getAllAvailableBooks();
    }

    public void addNewBook() {
        Author author;
        System.out.println("Title: ");
        String title = sc.next();

        System.out.println("Genre: ");
        String genre = sc.next();

        System.out.println("Author name: ");
        String nameAuthor = sc.next();

        author = library.getAuthorByName(nameAuthor);

        if (author != null) {
            author = library.getAuthorByName(nameAuthor);
        } else {
            System.out.println("Date of birth: ");
            String date = sc.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateBirth = LocalDate.parse(date, formatter);
            author = new Author();
            author.setId(AuthorCodeGenerator.getNextCode());
            author.setName(nameAuthor);
            author.setDateOfBirth(dateBirth);
        }
        library.addAuthor(author);

        Book book = new Book();
        book.setId(BookCodeGenerator.getNextCode());
        book.setAuthor(author);
        book.setTitle(title);
        book.setAvailable(true);
        book.setGenre(Genre.valueOf(genre));
        book.setDateRegistration(LocalDate.now());

        library.addBook(book);
        System.out.println("Book was successfully registered.");
    }

}