package pl.koneckimarcin.electricalprotocolsmanager.backup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
public class DatabaseBackupServiceTest {

    @InjectMocks
    private DatabaseBackupService service;

    @Test
    public void shouldCreateListOfQueriesFromFile() {
        //given
        service.setSchemaName("testSchema.sql");
        //when
        service.createQueriesListFromFile();
        //then
        assertThat(service.getAllQueries(), hasSize(3));
    }

//    @Test
//    public void shouldCatchExceptionWithMessageAndFileName() {
//        //given
//        String unexistingFileName = "unexistingFile.sql";
//        String expectedMessage = "Can not find file: ";
//        service.setSchemaName(unexistingFileName);
//        //when then
//        try {
//            service.createQueriesListFromFile();
//        } catch (Exception e) {
//            //fail();
//            Assertions.assertEquals(expectedMessage + unexistingFileName + "test", e.getMessage());
//        }
//    }
}
