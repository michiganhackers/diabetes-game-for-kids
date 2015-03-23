using UnityEngine;
using System.Collections;

public class CarbCounter : MonoBehaviour {
	// Use this for initialization
	public void SwitchScene(string scene_name) {
		Application.LoadLevel(scene_name);
	}
}
