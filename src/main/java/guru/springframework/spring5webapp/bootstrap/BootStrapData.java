package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner{


    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository= publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {



        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St PEtersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publisher count: "+ publisherRepository.count());

        Author eric = new Author("Eric","Evans");
        Book adde = new Book("Domain Driven design", "1234");

        eric.getBook().add(adde);
        adde.getAuthors().add(eric);

        //am adaugat cartea adde la publisher si am salvat-o in repo
        adde.setPublisher(publisher);
        publisher.getBooks().add(adde);
        //publisherRepository.save(publisher);


        authorRepository.save(eric);
        bookRepository.save(adde);



        Author rod =  new Author("Rod","Johson");
        Book noEJB = new Book("J2EE Development without EJB", "334455");

        rod.getBook().add(noEJB);
        noEJB.getAuthors().add(rod);

        //am adaugat cartea noEJB la publisher si am salvat-o in repo
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);
        //publisherRepository.save(publisher);


        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books :" +bookRepository.count());

        System.out.println("Publisher Number of Books: "+publisher.getBooks().size());

    }
}
