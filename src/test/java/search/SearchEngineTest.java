package search;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author nilstes
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchEngineTest {
    @Mock
    PageReader reader;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @InjectMocks
    SearchEngine se = new SearchEngine();


    @Before
    public void setUp() throws Exception {
        when(reader.readPage("vg.no")).thenReturn("ja nei hei hello kat nisse".split(" "));
        when(reader.readPage("tek.no")).thenReturn("data mobil hei hei".split(" "));
        when(reader.readPage("kake.no")).thenReturn("kake deig sukker egg mel is".split(" "));
        se.indexPage("vg.no");
        se.indexPage("tek.no");
        se.indexPage("kake.no");
    }

    @Test
    public void indexPage() throws Exception {
        String url = "elg.no";
        when(reader.readPage(url)).thenReturn("dyr elg skog blåbær".split(" "));
        se.indexPage(url);
        verify(reader).readPage(url);
    }

    @Test
    public void search() throws Exception {
        String ord = "hei";
        String word = "kake";
        List<String> hei = se.search(ord);
        assertEquals("tek.no", hei.get(0));
        assertEquals("vg.no", hei.get(1));
        assertEquals(2, hei.size());
        List<String> kake = se.search(word);
        assertNotEquals("vg.no", kake.get(0));
        assertNotEquals("tek.no", kake.get(0));
        exception.expect(IndexOutOfBoundsException.class);
        assertEquals(null, kake.get(1));
    }

    @Test
    public void searchNonExist() throws Exception {
        // TODO: 12.10.2016 Sjekk med assert() om søkemotoren tåler at man søker på ord som ikke er indeksert. Fiks SearchEngine hvis det ikke er tilfelle.
        String finnesIkke = "Overbuljongterningpakkmesterassistent";
        try {
            se.search(finnesIkke).isEmpty();
        } catch (Exception e) {
            assertTrue("Search with non indexed word", false);
        }
    }

}
