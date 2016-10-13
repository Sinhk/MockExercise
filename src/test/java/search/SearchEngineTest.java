package search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

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
        when(reader.readPage("vg.no")).thenReturn(new String[]{"ja", "nei", "hei", "hello"});
        when(reader.readPage("tek.no")).thenReturn(new String[]{"data", "mobil", "hei", "hei", "kat", "nisse"});
        when(reader.readPage("kake.no")).thenReturn(new String[]{"kake", "deig", "sukker", "egg", "mel", "is"});
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
        assert hei.get(0).equals("tek.no");
        assert hei.get(1).equals("vg.no");
        assert hei.size() == 2;

        // TODO: 12.10.2016 Sjekk med assert() om søkemotoren tåler at man søker på ord som ikke er indeksert. Fiks SearchEngine hvis det ikke er tilfelle.
        /*String finnesIkke = "Overbuljongterningpakkmesterassistent";
        assert se.search(finnesIkke).isEmpty();*/
    }

}
