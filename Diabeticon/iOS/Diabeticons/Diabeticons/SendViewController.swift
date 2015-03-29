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
        shareWithActivityMenu()
    }
    
    func sendAsSms() {
        let messageview = MFMessageComposeViewController(rootViewController: self)
        messageview.addAttachmentURL(image?.url, withAlternateFilename: "") // TODO: add default file
        presentViewController(messageview, animated: true, completion: nil)
    }
    
    func shareWithActivityMenu() {
        let activity_view = UIActivityViewController(activityItems: [image!.url], applicationActivities: nil)
        if activity_view.respondsToSelector("popoverPresentationController") &&
           (UIDevice.currentDevice().userInterfaceIdiom == UIUserInterfaceIdiom.Pad){
            
            let popover = UIPopoverController(contentViewController: activity_view)
            popover.presentPopoverFromRect(
                CGRect(x: self.view.frame.size.width/2,
                    y: self.view.frame.size.height,
                    width: 0, height: 0),
                inView: self.view,
                permittedArrowDirections: UIPopoverArrowDirection.Any,
                animated: true)

            
        } else {
            presentViewController(activity_view, animated: true, completion: nil)
        }

        
    }
    
    func messageComposeViewController(controller: MFMessageComposeViewController!, didFinishWithResult result: MessageComposeResult) {
        controller.dismissViewControllerAnimated(true, completion: nil)
    }
    override func viewWillAppear(animated: Bool) {
        if image != nil {
            imageview.image = image
            navigationItem.title = image!.name
        } else {
            self.navigationController?.popToRootViewControllerAnimated(false)
        }
    }
}
