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
    public Button getDataButton; // TODO: Rework this crap
    public Text outputText;      // Where output text will be shown

    // Debug function: Outputs "output" to the log as well as the screen debugger
    void outputInfo(string output) {
        outputText.text = outputText.text + "\n" + output;
    }

    // Creates the database, should only happen once
    void CreateDB() {
        outputInfo("Trying to create database...");
        try
        {
            //Create tables
            dConnector.CreateChapterTables();
            //Lock DB with key
            dConnector.SetKey(DBKey);

            outputInfo("Data created successfully");

            //Flip NewDB
            newDB = 1;
            PlayerPrefs.SetInt("newDB", newDB);

            // Disable the createTables button
            createTables.enabled = false;
            createTables.image.color = new Color(1f, 1f, 1f, 0.3f);

        }
        catch (Exception)
        {
            outputInfo("Database already exists!");
            //Database already exists
            createTables.enabled = false;
            createTables.image.color = new Color(1f, 1f, 1f, 0.3f);
            newDB = 1;
            PlayerPrefs.SetInt("newDB", newDB);

        }
    }

	// Use this for initialization
	void Start () {
        // Empty out the output text
        outputText.text = "";

        dConnector = new DatabaseConnector(DatabaseName);

        // Reset database if necessary
        if (resetDatabase)
        {
            outputInfo("Database reset");
            // Clearing out saved newDB value will later on make the db
            PlayerPrefs.DeleteKey("newDB");
        }

        // Get if a newDB is necessary or not
        newDB = PlayerPrefs.GetInt("newDB");
        if (newDB == 0)
        {
            outputInfo("newDB was zero");
            createTables.onClick.AddListener(() =>
            {
                CreateDB();
            });
        }
        else
        {
            outputInfo("newDB was one");
            // Disable create tables button
            createTables.enabled = false;
            createTables.image.color = new Color(1f, 1f, 1f, 0.3f);
        }

        getDataButton.onClick.AddListener(() =>
        {
            outputInfo("Submit DB: Not yet implemented");

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
