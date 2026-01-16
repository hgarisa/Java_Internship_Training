package Inner_Outer_Classes.Easy_level_Questions.Library_BookCounter;

public class Library {
    public void dailyReport() {
        class BookCounter {

            public int number;
            public BookCounter(int number)
            {
            this.number = number;

            }
            public void countIssuedBooks() {

                System.out.println("Number of books issued today are " + number);
            }

        }
        BookCounter bk = new BookCounter(10);
        bk.countIssuedBooks();

    }
}
