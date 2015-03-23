using UnityEngine;
using UnityEngine.UI;
using System.Collections;
[RequireComponent(typeof(RectTransform))]
public class ScrollViewScript : MonoBehaviour {

	public Object cell_prototype;
	public GameObject parent;
	public Canvas canvas;
	private ArrayList cells = new ArrayList();
	private float last_position = 2.3f;
	private float CELL_SPACING = 0.2f;


	void create_cell(string title="") {
		GameObject cell = Instantiate (cell_prototype, new Vector3(0,last_position,0), Quaternion.identity) as GameObject;
		RectTransform rt = cell.GetComponent<RectTransform> ();
		cell.transform.SetParent (parent.transform);
		cell.transform.localScale = new Vector3 (1, 1, 1);
		float cell_height = .9f;
		cell.GetComponentInChildren<Text> ().text = title;
		last_position -= cell_height + CELL_SPACING;
//		print (last_position);

		Vector3[] corners = new Vector3[4];
		cell.GetComponentInParent<RectTransform> ().GetWorldCorners (corners);
//		if (
		
	}

	void Start() {
		for (int i = 1; i < 25; i++) {
			string name = "Meal #" + i.ToString();
			create_cell (name);
		}
	}
}
