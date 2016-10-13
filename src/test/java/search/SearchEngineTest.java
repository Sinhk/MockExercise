package search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

    @InjectMocks
    SearchEngine se = new SearchEngine();

    @Before
    public void setUp() throws Exception {
        when(reader.readPage("vg.no")).thenReturn("ja nei hei hello kat nisse".split(" "));
        when(reader.readPage("tek.no")).thenReturn("data mobil hei hei".split(" "));
        when(reader.readPage("kake.no")).thenReturn("kake deig sukker egg mel is".split(" "));
    }

    @Test
    public void indexPage() throws Exception {
        String url = "vg.no";
        se.indexPage(url);
        verify(reader).readPage(url);
    }

    @Test
    public void search() throws Exception {
        String ord = "hei";
        se.indexPage("vg.no");
        se.indexPage("tek.no");
        se.indexPage("kake.no");
        List<String> hei = se.search(ord);
        assertEquals(hei.get(0), "tek.no");
        assertEquals(hei.get(1), ("vg.no"));
        assertEquals(hei.size(), 2);

        // TODO: 12.10.2016 Sjekk med assert() om søkemotoren tåler at man søker på ord som ikke er indeksert. Fiks SearchEngine hvis det ikke er tilfelle.
        String finnesIkke = "Overbuljongterningpakkmesterassistent";
        try {
            se.search(finnesIkke).isEmpty();
        } catch (Exception e) {
            assertTrue("Search with non indexed word", false);
        }
    }

}
