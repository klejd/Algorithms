package ford.com.trackandartistinfo;

import ford.com.trackandartistinfo.controller.RestHandler;
import ford.com.trackandartistinfo.model.Artist;
import ford.com.trackandartistinfo.model.Image;
import ford.com.trackandartistinfo.model.Track;
import ford.com.trackandartistinfo.service.MusicService;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.regex.Matcher;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackAndArtistInfoApplicationTests {

    private MockMvc mockMvc;

    @InjectMocks
    private RestHandler restHandler;

    @MockBean
    private MusicService musicService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(restHandler)
                .build();

        /*
            The init() method runs MockitoAnnotations.initMocks(this) using this instance as the argument.
            This sets up our mocks before each test. Passing this instance will make Mockito acknowledge the
            @InjectMocks and the @Mocks annotations and that they should be pushed together.
         */
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTopTrackTest()throws Exception {
        Image[] images = new Image[2];
        images[0] = new Image("url_1", "contextUrl_1");
        images[1] = new Image("url_1", "contextUrl_1");

        Artist artist = new Artist("adele","yes", images);
        Track track = new Track("hello","233849349","adfadfadfadfadf", artist);

        when(musicService.getTrackByRegion("canada")).thenReturn(track);

        mockMvc.perform(get("/api/trackinfo/{region}", "canada")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is("hello")))
                .andExpect(jsonPath("$.listeners", Matchers.is("233849349")))
                .andExpect(jsonPath("$.lyrics", Matchers.is("adfadfadfadfadf")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(4)));

    }

    @Test
    public void contextLoads() {
    }

}
