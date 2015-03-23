using UnityEngine;
using System.Collections.Generic;
using System.Linq;

public class ListViewColumn<T>
{
	public readonly bool IsAction;
	public string Name;
	public readonly string ActionName;
	public readonly System.Action<T> Action;
	public readonly System.Func<T, string> Output;
	private readonly System.Comparison<T> Compare;
	public float Width;
	public bool SortAsc = true;
	
	public ListViewColumn(string name, string actionName, System.Action<T>  action, float w = 1)
	{
		Name = name;
		ActionName = actionName;
		Action = action;
		IsAction = true;
		Width = w;
	}
	
	public ListViewColumn(string name, System.Func<T, string> output, System.Comparison<T> compare, float w = 1)
	{
		Name = name;
		Output = output;
		IsAction = false;
		Width = w;
		Compare = compare;
	}
	
	public int Sort(List<T> input, int i)
	{
		T obj = default(T);
		if (i != -1)
		{
			obj = input[i];
		}
		input.Sort(Compare);
		if (!SortAsc)
		{
			input.Reverse();
		}
		SortAsc = !SortAsc;
		return i == -1 ? -1 : input.IndexOf(obj);
	}
}