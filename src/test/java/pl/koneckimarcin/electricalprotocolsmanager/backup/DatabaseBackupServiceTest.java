package pl.koneckimarcin.electricalprotocolsmanager.backup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;

@ExtendWith(MockitoExtension.class)
public class DatabaseBackupServiceTest {

    @InjectMocks
    private DatabaseBackupService service;

    @Mock
    BufferedReader reader;

    @Test
    public void test() {

    }
}
