package fit.se;

public class XmlToJsonAdapter implements IJsonService{
    private XmlLibrary xmlLibrary;

    public XmlToJsonAdapter(XmlLibrary xmlLibrary) {
        this.xmlLibrary = xmlLibrary;
    }

    @Override
    public void processJsonData() {
        // 1. Lấy dữ liệu XML
        String xmlData = xmlLibrary.getXmlData();

        // 2. Convert XML sang JSON (Giả lập)
        String jsonData = convertXmlToJson(xmlData);

        // 3. Xử lý
        System.out.println("Adapter đã xử lý dữ liệu JSON: " + jsonData);
    }

    private String convertXmlToJson(String xml) {
        // Logic chuyển đổi thực tế sẽ nằm ở đây
        return "{ \"data\": { \"item\": \"Hello\" } }";
    }
}
