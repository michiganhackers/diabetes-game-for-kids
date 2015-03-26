//
//  IconEnumerater.swift
//  Diabeticons
//
//  Created by Spruce Bondera on 3/25/15.
//  Copyright (c) 2015 michiganhackers. All rights reserved.
//

import Foundation
import UIKit

class IconEnumerator : NSObject {
    var icons : [NamedImage]
    override init() {
        icons = []
        var path = NSBundle.mainBundle().resourcePath! + "/Library"
        let manager = NSFileManager.defaultManager()
        println(manager.fileExistsAtPath(path))
        for file in manager.contentsOfDirectoryAtPath(path, error: nil) as [String] {
            var name = split_on_caps(file.stringByDeletingPathExtension)
            icons.append(NamedImage(name: name, filepath: path + "/" + file)!)
        }
        icons.sort({$0.name < $1.name})
        super.init()
    }
}

class NamedImage : UIImage {
    var name: String
    init?(name: String, filepath: String) {
        self.name = name
        super.init(contentsOfFile: filepath)
    }

    required init(coder aDecoder: NSCoder) {
        name = aDecoder.decodeObjectForKey("name") as String
        super.init(coder: aDecoder)
    }
}

func split_on_caps(string: String) -> String {
    var newstring = string.substringToIndex(string.startIndex.successor())
    for c in string.substringFromIndex(string.startIndex.successor()) {
        let s = String(c)
        if s.lowercaseString != s {
            newstring += " " + s
        } else {
            newstring += s
        }
    }
    return newstring
}