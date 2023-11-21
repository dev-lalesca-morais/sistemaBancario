import static org.mockito.Mockito.*;

import connection.Conexao;
import org.junit.Before;
import org.junit.Test;
import service.ContaBancariaService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContaBancariaServiceTest {
    private Conexao mockConexao;
    private Connection mockConnection;
    private Statement mockStatement;
    private ContaBancariaService contaBancariaService;
    private ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        mockConexao = mock(Conexao.class);
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConexao.fazerConexao()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        contaBancariaService = new ContaBancariaService(mockConexao);
    }

    @Test
    public void testInserirConta() throws SQLException {
        when(mockStatement.executeUpdate(anyString())).thenReturn(1);

        contaBancariaService.inserirConta(1000, 1);
        verify(mockStatement).executeUpdate(anyString());
    }

    @Test
    public void testConsultaTodasAsContas_ContasEncontradas() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("numero_conta")).thenReturn("123456");

        contaBancariaService.consultaTodasAsContas();
        verify(mockStatement).executeQuery("SELECT * FROM ContaBancaria");
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet).getInt("id");
        verify(mockResultSet).getString("numero_conta");
    }

    @Test
    public void testConsultaTodasAsContas_NenhumaContaEncontrada() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);

        contaBancariaService.consultaTodasAsContas();

        verify(mockStatement).executeQuery("SELECT * FROM ContaBancaria");
        verify(mockResultSet).next();

    }

    @Test
    public void testDeletarConta() throws SQLException {
        when(mockStatement.executeUpdate(anyString())).thenReturn(1);

        contaBancariaService.deletarConta(1);
        verify(mockStatement).executeUpdate(anyString());
    }
}

