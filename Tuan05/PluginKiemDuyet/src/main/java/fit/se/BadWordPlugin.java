package fit.se;


import fit.se.cms_kernelarchiture.plugin.CmsPlugin;

public class BadWordPlugin implements CmsPlugin {
    private boolean active = false;

    @Override
    public String getPluginId() { return "bad-word-filter"; }

    @Override
    public String getPluginName() { return "Bộ lọc ngôn từ"; }

    @Override
    public boolean isActive() { return active; }

    @Override
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String execute(String content) {
        if (!active) return content;

        // Logic: Thay thế các từ cấm thành dấu ***
        String safeContent = content.replaceAll("(?i)(ngu|đần|cút|chết|cok)", "***");
        return "🛡️ [Đã quét ngôn từ]\n" + safeContent;
    }
}