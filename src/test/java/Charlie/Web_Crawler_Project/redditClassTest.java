package Charlie.Web_Crawler_Project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class redditClassTest {
redditClass reddit;

    @BeforeEach
    void setUp() {
        reddit = new redditClass();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testValidTitle() {
        reddit.setTitle("Hello World");
        assertEquals("Hello World", reddit.getTitle());
    }

    @Test
    void testValidUrl() {
        reddit.setUrl("https://www.reddit.com");
        assertEquals("https://www.reddit.com", reddit.getUrl());
    }

    @Test
    void testValidComments() {
        reddit.setComments(12345L);
        assertEquals(12345, reddit.getComments());
    }

    @Test
    void testCommentsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            reddit.setComments(-12345L);
        });
    }

    @Test
    void testValidUpvotes() {
        reddit.setUpvotes(0.94);
        assertEquals(0.94, reddit.getUpvotes());
    }

    @Test
    void testUpvotesNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            reddit.setUpvotes(-0.94);
        });
    }
}