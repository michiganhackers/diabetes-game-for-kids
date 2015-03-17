using UnityEngine;
using System.Collections;
using System;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine.EventSystems;

public class SQLiteDataTester : MonoBehaviour {

    public bool resetDatabase = false; // If checked, will reset entire database
    public string DBKey = "dat key"; // Database is encrypted, this is the key
    public string DatabaseName = "test.db";

    private int newDB = 0; // 0 if need to create new database
    private DatabaseConnector dConnector;

    public Button createTables; // Enabled/disabled if need to create tables
    public Button submitButton; // TODO: Rework this crap

    // Creates the database, should only happen once
    void CreateDB()
    {

    }

	// Use this for initialization
	void Start () {
        dConnector = new DatabaseConnector(DatabaseName);

        // Reset database if necessary
        if (resetDatabase)
        {
            // Clearing out saved newDB value will later on make the db
            PlayerPrefs.DeleteAll();
        }

        // Get if a newDB is necessary or not
        newDB = PlayerPrefs.GetInt("newDB");
        if (newDB == 0)
        {
            createTables.onClick.AddListener(() =>
            {
                try
                {
                    //Create tables
                    dConnector.CreateChapterTables();
                    //Lock DB with key
                    dConnector.SetKey(DBKey);
                    //Flip NewDB
                    newDB = 1;
                    PlayerPrefs.SetInt("newDB", newDB);
                    createTables.enabled = false;
                    createTables.image.color = new Color(1f, 1f, 1f, 0.3f);

                }
                catch (Exception)
                {
                    //Database already exist
                    createTables.enabled = false;
                    createTables.image.color = new Color(1f, 1f, 1f, 0.3f);
                    newDB = 1;
                    PlayerPrefs.SetInt("newDB", newDB);

                }
            });
        }
        else
        {
            createTables.enabled = false;
            createTables.image.color = new Color(1f, 1f, 1f, 0.3f);
        }

        submitButton.onClick.AddListener(() =>
        {
            Debug.Log("Submit DB: Not yet implemented");

            //string pass = password.text;
            //if (pass == DBKey)
            //{
            //    //Unlock Db with KEY
            //    dConnector.Key(DBKey);
            //    generateLevels();
            //    passwordPanel.SetActive(false);
            //}
            //else
            //{
            //    password.text = "Invalid Password";
            //}
        });
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
