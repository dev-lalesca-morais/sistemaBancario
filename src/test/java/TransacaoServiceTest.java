import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Before;
import org.junit.Test;
import service.TransacaoService;

public class TransacaoServiceTest {
    private Connection mockConnection;
    private Statement mockStatement;
    private TransacaoService transacaoService;

    @Before
    public void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        transacaoService = new TransacaoService(mockConnection, mockStatement);
    }

    @Test
    public void testListarTransacoesDaConta() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        transacaoService.listarTransacoesDaConta(1);
        verify(mockStatement).executeQuery(anyString());
        verify(mockResultSet).next();
    }

    @Test
    public void testFazerDeposito() throws SQLException {
        when(mockStatement.executeUpdate(anyString())).thenReturn(1);

        transacaoService.fazerDeposito(1, BigDecimal.TEN);
        verify(mockStatement).executeUpdate(anyString());
    }

    @Test
    public void testFazerSaque() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getBigDecimal("saldo")).thenReturn(BigDecimal.TEN);

        when(mockStatement.executeUpdate(anyString())).thenReturn(1);

        transacaoService.fazerSaque(1, BigDecimal.ONE);
        verify(mockStatement).executeQuery(anyString());
        verify(mockResultSet).next();
        verify(mockResultSet).getBigDecimal("saldo");
        verify(mockStatement).executeUpdate(anyString());
    }

    @Test
    public void testListarContasDeUmCliente() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        transacaoService.listarContasDeUmCliente(1);
        verify(mockStatement).executeQuery(anyString());
        verify(mockResultSet).next();
    }
}