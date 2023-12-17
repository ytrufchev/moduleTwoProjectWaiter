import entities.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class setTableTest {
    @Test
    public void testTableBetweenOneAndTen(){
        Table table = new Table();
        table.setNumberOfTable(6);
        Assertions.assertEquals(6, table.getNumberOfTable());
    }
}
