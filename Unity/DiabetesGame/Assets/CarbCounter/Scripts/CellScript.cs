using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class CellScript : MonoBehaviour {
	public Button button;
	public Sprite newsprite;
	private bool switchstate = false;

	void Start() {
		button.onClick.RemoveAllListeners ();
		button.onClick.AddListener (highlightToggle);
	}

	void highlightToggle() {
		if (switchstate) {
			button.image.overrideSprite = newsprite;
		} else {
			button.image.overrideSprite = null;
		}
		switchstate = !switchstate;
	}
}
