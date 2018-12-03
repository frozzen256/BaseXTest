import javax.xml.xquery.*;
import javax.xml.namespace.QName;
import net.xqj.basex.BaseXXQDataSource;

public class Main
{
    public static void main(String[] args) throws XQException
    {
        XQDataSource xqs = new BaseXXQDataSource();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "1984");

        // Change USERNAME and PASSWORD values
        XQConnection conn = xqs.getConnection("admin", "admin");

        String sq = "declare namespace ns2 = \"http://zakupki.gov.ru/oos/export/1\";\n" +
                "        declare default element namespace \"http://zakupki.gov.ru/oos/types/1\";\n" +
                "\n" +
                "        for $i in\n" +
                "        collection(\"Contracts\")/ns2:export/ns2:contract/customer/fullName\n" +
                "        return\n" +
                "                json:serialize($i, map {'format': 'jsonml'})";

        String sq1 = "declare namespace ns2 = \"http://zakupki.gov.ru/oos/export/1\"; " +
                "declare default element namespace \"http://zakupki.gov.ru/oos/types/1\"; " +
                "for $i in collection(\"Contracts\")/ns2:export/ns2:contract/customer/fullName return " +
                "$i/data()";
        final long time = System.nanoTime();

        XQPreparedExpression xqpe =
                conn.prepareExpression(sq1);

       // xqpe.bindString(new QName("x"), "Hello World!", null);

        XQResultSequence rs = xqpe.executeQuery();

        final double ms1 = (System.nanoTime() - time) / 1000000.0d;
        System.out.println("\n\n" + ms1 + " ms");

        while(rs.next())
            System.out.println(rs.getItemAsString(null));

        final double ms = (System.nanoTime() - time) / 1000000.0d;
        System.out.println("\n\n" + ms + " ms");



        conn.close();
    }
}