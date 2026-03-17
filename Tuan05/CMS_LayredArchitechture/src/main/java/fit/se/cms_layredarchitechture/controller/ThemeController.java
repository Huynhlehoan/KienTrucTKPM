package fit.se.cms_layredarchitechture.controller;
import fit.se.cms_layredarchitechture.entity.Theme;
import fit.se.cms_layredarchitechture.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theme")
public class ThemeController {
    @Autowired private ThemeService themeService;
    @GetMapping
    public Theme getTheme() { return themeService.getActiveTheme(); }
    @PostMapping
    public Theme changeTheme(@RequestParam String mode) { return themeService.updateTheme(mode); }
}