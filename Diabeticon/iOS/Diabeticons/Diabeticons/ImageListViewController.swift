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
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "send_segue" {
            let dest = segue.destinationViewController as SendViewController
            dest.image = IconEnumerator.sharedInstance.icons[tableView.indexPathForSelectedRow()!.row]
        }
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCellWithIdentifier("emoticell") as UITableViewCell
        let image = IconEnumerator.sharedInstance.icons[indexPath.row]
        let label = cell.contentView.viewWithTag(1) as UILabel
        label.text = image.name
        let imageview = cell.contentView.viewWithTag(2) as UIImageView
        imageview.image = image
        return cell
    }

    
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return IconEnumerator.sharedInstance.icons.count
    }

}

