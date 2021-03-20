package Charlie.Web_Crawler_Project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class twitterClassTest extends twitterClass {
    twitterClass twitter;

    @BeforeEach
    void setUp() { twitter = new twitterClass();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testValidName() {
        twitter.setName("user");
        assertEquals("user", twitter.getName());
    }

    @Test
    void testValidTweet() {
        twitter.setTweet("Hello World");
        assertEquals("Hello World", twitter.getTweet());
    }

    @Test
    void testValidRtcount() {
        twitter.setRtcount(1234);
        assertEquals(1234,twitter.getRtcount());
    }

    @Test
    void testRtcountNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            twitter.setRtcount(-1234);
        });
    }
}