//
//  SendViewController.swift
//  Diabeticons
//
//  Created by Spruce Bondera on 3/26/15.
//  Copyright (c) 2015 michiganhackers. All rights reserved.
//

import UIKit
import MessageUI

class SendViewController: UIViewController, MFMessageComposeViewControllerDelegate {
    var image: NamedImage? = nil
    @IBOutlet var imageview: UIImageView!
    @IBAction func shareButtonPressed(sender: UIButton) {
        let messageview = MFMessageComposeViewController(rootViewController: self)
        messageview.addAttachmentURL(image?.url, withAlternateFilename: "") // TODO: add default file
        presentViewController(messageview, animated: true, completion: nil)
        
    }
    
    func messageComposeViewController(controller: MFMessageComposeViewController!, didFinishWithResult result: MessageComposeResult) {
        controller.dismissViewControllerAnimated(true, completion: nil)
    }
    override func viewWillAppear(animated: Bool) {
        if image != nil {
            imageview.image = image
            navigationItem.title = image!.name
        }
    }
}
