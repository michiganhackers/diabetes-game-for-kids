using UnityEngine;
using System.Collections.Generic;
using System.Linq;

public class ListView<T>
{
	public List<T> Items = new List<T>();
	public List<ListViewColumn<T>> Columns = new List<ListViewColumn<T>>();
	public int Selected = -1;
	public T SelectedItem 
	{
		get 
		{
			return Selected>-1?Items[Selected]:default(T);
		}
		set 
		{
			Selected = Items.IndexOf(value);
		}
	}
	private float scroll = 0;
	public Rect Rect;
	
	public ListView(Rect position)
	{
		Rect = position;
	}
	
	public void Draw(Vector2 offset)
	{
		if (Items.Count==0)
		{
			Selected = -1;
		}
		var mouse = new Vector2(Input.mousePosition.x - offset.x, (Screen.height - Input.mousePosition.y) - offset.y);
		GUI.Box(Rect, "");
		var useScroll = Rect.height - 24 < Items.Count * 24;
		var s = Columns.Sum(x => x.Width);
		var w = Columns.Select(x => x.Width * (Rect.width - (useScroll ? 16 : 0)) / s).ToArray();
		var ws = 0f;
		for (int i = 0; i < Columns.Count; i++)
		{
			if (Columns[i].IsAction)
			{
				GUI.Box(new Rect(Rect.x + ws, Rect.y, w[i], 24), Columns[i].Name);
			}
			else
			{
				if (GUI.Button(new Rect(Rect.x + ws, Rect.y, w[i], 24), Columns[i].Name))
				{
					Selected = Columns[i].Sort(Items, Selected);
				}
			}
			ws += w[i];
		}
		if (Columns.Count == 0)
		{
			return;
		}
		var count = (int)Mathf.Floor((Rect.height - 24) / 24);
		for (int i = 0; i < count; i++)
		{
			var ii = i + (int)scroll;
			if (ii >= Items.Count)
			{
				break;
			}
			int j = 0;
			ws = 0;
			foreach (var column in Columns)
			{
				if (column.IsAction)
				{
					if (GUI.Button(new Rect(Rect.x + ws, Rect.y + 24 + i * 24, w[j], 24), column.ActionName))
					{
						column.Action(Items[ii]);
					}
				}
				else
				{
					GUI.Box(new Rect(Rect.x + ws, Rect.y + 24 + i * 24, w[j], 24), column.Output(Items[ii]));
				}
				ws += w[j];
				j++;
			}
			if (Input.GetMouseButtonUp(0) &&
			    mouse.x >= Rect.xMin &&
			    mouse.x <= Rect.xMax - (useScroll ? 16 : 0) &&
			    mouse.y >= Rect.y + 24 + i * 24 &&
			    mouse.y <= Rect.y + 48 + i * 24)
			{
				Selected = ii;
			}
		}
		if (useScroll)
		{
			scroll = Mathf.Round(
				GUI.VerticalScrollbar(
				new Rect(Rect.x + Rect.width - 16, Rect.y, 16, Rect.height),
				scroll,
				1,
				0,
				Items.Count - count + 1));
			if (scroll < 0)
			{
				scroll = 0;
			}
		}
		if (Selected >= scroll && Selected < scroll+count)
		{
			var i = Selected - scroll;
			GUI.Box(new Rect(Rect.x, Rect.y + 24 + i * 24, Rect.width - (useScroll ? 16 : 0), 24), "");
		}
	}
}