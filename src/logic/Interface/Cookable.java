package logic.Interface;

import logic.cooking.CookState;

public interface Cookable {
	CookState getCookState();

	void setCookState(CookState state);
}
