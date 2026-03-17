package fit.se.cms_layredarchitechture.service;
import fit.se.cms_layredarchitechture.entity.Theme;
import fit.se.cms_layredarchitechture.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeService {
    @Autowired private ThemeRepository themeRepository;

    public Theme getActiveTheme() {
        return themeRepository.findById(1L).orElse(new Theme());
    }

    public Theme updateTheme(String mode) {
        Theme theme = getActiveTheme();
        theme.setId(1L);
        theme.setMode(mode);
        return themeRepository.save(theme);
    }
}
