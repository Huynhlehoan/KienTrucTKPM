package fit.se.cms_kernelarchiture.plugin;
import org.springframework.stereotype.Component;

@Component
public class SeoPlugin implements CmsPlugin {
    private boolean active = false;

    @Override public String getPluginId() { return "seo-tool"; }
    @Override public String getPluginName() { return "Công cụ chuẩn hóa SEO"; }
    @Override public boolean isActive() { return active; }
    @Override public void setActive(boolean active) { this.active = active; }

    @Override
    public String execute(String content) {
        if (!active) return content;
        // Logic giả lập: Phân tích SEO và gắn badge xanh nếu nội dung dài
        String seoStatus = content.length() > 50 ? "✅ Tốt" : "❌ Quá ngắn (Cần bổ sung)";
        return "🚀 [SEO Plugin - Đánh giá: " + seoStatus + "]\n\n" + content;
    }
}