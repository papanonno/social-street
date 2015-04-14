package socialstreet.view;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "language")
@SessionScoped
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Map<String, Object> countries;

	@Getter
	@Setter
	private String localeCode;

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	static {
		countries = new LinkedHashMap<String, Object>();
		countries.put("italiano", Locale.ITALIAN);
		countries.put("english", Locale.ENGLISH);
	}

	// value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {

		String newLocaleValue = e.getNewValue().toString();

		// loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : countries.entrySet()) {
			if (entry.getValue().toString().equals(newLocaleValue)) {
				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
			}
		}
	}
}
