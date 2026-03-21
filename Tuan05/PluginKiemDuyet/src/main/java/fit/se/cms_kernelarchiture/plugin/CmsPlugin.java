package fit.se.cms_kernelarchiture.plugin;

public interface CmsPlugin {
    String getPluginId();   // Mã định danh của Plugin
    String getPluginName(); // Tên hiển thị trên giao diện
    boolean isActive();     // Trạng thái bật/tắt
    void setActive(boolean active);

    // Hook: Can thiệp vào nội dung bài viết trước khi hiển thị cho người dùng
    String execute(String content);
}