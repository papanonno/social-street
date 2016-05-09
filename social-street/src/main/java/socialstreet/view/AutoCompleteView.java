package socialstreet.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import lombok.Data;

import org.primefaces.event.SelectEvent;

import socialstreet.model.Theme;
 
@ManagedBean
@Data
public class AutoCompleteView {
     
    private String txt1;
    private String txt2;
    private String txt3;
    private String txt4;
    private String txt5;
    private String txt6;
    private String txt7;
    private String txt8;
    private Theme theme1;
    private Theme theme2;
    private Theme theme3;
    private Theme theme4;
    private List<Theme> selectedThemes;
     
    @ManagedProperty("#{themeBean}")
    private ThemeBean bean;
     
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }
     
    public List<Theme> completeTheme(String query) {
        List<Theme> allThemes = bean.getThemes();
        List<Theme> filteredThemes = new ArrayList<Theme>();
         
        for (int i = 0; i < allThemes.size(); i++) {
            Theme skin = allThemes.get(i);
            if(skin.getName().toLowerCase().startsWith(query)) {
                filteredThemes.add(skin);
            }
        }
         
        return filteredThemes;
    }
     
    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }
 
    public char getThemeGroup(Theme theme) {
        return theme.getDisplayName().charAt(0);
    }
}