//
//  ViewController.swift
//  Diabeticons
//
//  Created by Spruce Bondera on 3/25/15.
//  Copyright (c) 2015 michiganhackers. All rights reserved.
//

import UIKit

class ImageListViewController: UITableViewController {

    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.rowHeight = 75
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCellWithIdentifier("emoticell") as UITableViewCell
        let index = indexPath.row
        let image = IconEnumerator.sharedInstance.icons[index]
        let name = image.name
        let label = cell.contentView.viewWithTag(1) as UILabel
        label.text = name
        let imageview = cell.contentView.viewWithTag(2) as UIImageView
        imageview.image = image
        return cell
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return IconEnumerator.sharedInstance.icons.count
    }

}

