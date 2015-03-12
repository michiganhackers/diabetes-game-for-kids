//
//  LoginViewController.swift
//  DiabetesGameParent
//
//  Created by Spruce Bondera on 3/6/15.
//  Copyright (c) 2015 michiganhackers. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {
    var username = ""
    @IBOutlet var name: UITextField!
    @IBAction func nameChanged(sender: UITextField) {
        self.username = sender.text
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        name.attributedPlaceholder = NSAttributedString(string: name.placeholder!, attributes: [NSForegroundColorAttributeName: UIColor.blackColor()])
        var gestureRecognizer = UITapGestureRecognizer(target: self, action: "closeKeyboards")
        gestureRecognizer.cancelsTouchesInView = false
        self.view.addGestureRecognizer(gestureRecognizer)
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        
    }
    

    func closeKeyboards() {
        self.view.endEditing(false)
    }

}
