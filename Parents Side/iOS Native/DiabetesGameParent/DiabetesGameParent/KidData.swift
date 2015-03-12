//
//  KidData.swift
//  DiabetesGameParent
//
//  Created by Spruce Bondera on 3/7/15.
//  Copyright (c) 2015 michiganhackers. All rights reserved.
//

import Foundation

let persisted = KidData.persisted_data
let KidDataSharedInstance = persisted != nil ? persisted! : KidData()

let KID_DATA_KEY = "KIDS"

class KidData: NSObject, NSCoding {
    class var sharedInstance: KidData {
        return KidDataSharedInstance
    }
    
    class var persisted_data: KidData? {
        get {
        if let data = NSUserDefaults.standardUserDefaults().dataForKey(KID_DATA_KEY) {
        return NSKeyedUnarchiver.unarchiveObjectWithData(data) as KidData?
    } else {
        return nil
        }
        }
        set {
            var defaults = NSUserDefaults.standardUserDefaults()
            defaults.setObject(NSKeyedArchiver.archivedDataWithRootObject(newValue!), forKey: KID_DATA_KEY)
            defaults.synchronize()
        }
    }
    var kids: [Kid] = []
    let KIDS_DATA_ARRAY_KEY = "KIDS_DATA_ARRAY_KEY"
    override init() {
        super.init()
        sync()
    }
    
    required init(coder aDecoder: NSCoder) {
        self.kids = aDecoder.decodeObjectForKey(KIDS_DATA_ARRAY_KEY) as [Kid]
    }
    
    func encodeWithCoder(aCoder: NSCoder) {
        aCoder.encodeObject(self.kids, forKey: KIDS_DATA_ARRAY_KEY)
    }
    
    
    func sync() {
        KidData.persisted_data = self
    }
}

class Kid: NSObject, NSCoding {
    var name: String
    let KID_NAME_KEY = "KID_NAME_KEY"
    var email: String
    let EMAIL_KEY = "EMAIL_KEY"
    var calorie_data: [Day]
    let CALORIE_DATA_KEY = "CALORIE_DATA_KEY"
    init(name: String, email: String, calories: [Day]) {
        self.name = name
        self.email = email
        self.calorie_data = calories
        super.init()
    }
    
    required init(coder aDecoder: NSCoder) {
        self.name = aDecoder.decodeObjectForKey(KID_NAME_KEY) as String
        self.email = aDecoder.decodeObjectForKey(EMAIL_KEY) as String
        self.calorie_data = aDecoder.decodeObjectForKey(CALORIE_DATA_KEY) as [Day]
    }
    
    func encodeWithCoder(aCoder: NSCoder) {
        aCoder.encodeObject(self.name, forKey: KID_NAME_KEY)
        aCoder.encodeObject(self.email, forKey: EMAIL_KEY)
        aCoder.encodeObject(self.calorie_data, forKey: CALORIE_DATA_KEY)
    }
}

class Day: NSObject, NSCoding {
    var date: NSDate
    let DATE_KEY = "DATE_KEY"
    var meals: [Food: NSDate] = [:]
    let MEALS_KEY = "MEALS_KEY"
    init(date: NSDate, meals: [Food: Int]) {
        self.date = date
        for items in meals {
            var time = NSTimeInterval(items.1)
            self.meals[items.0] = NSDate(timeInterval: time, sinceDate: date)
        }
        super.init()
    }
    
    required init(coder aDecoder: NSCoder) {
        date = aDecoder.decodeObjectForKey(DATE_KEY) as NSDate
        meals = aDecoder.decodeObjectForKey(MEALS_KEY) as [Food: NSDate]
    }
    
    func encodeWithCoder(aCoder: NSCoder) {
        aCoder.encodeObject(date, forKey: DATE_KEY)
        aCoder.encodeObject(meals, forKey: MEALS_KEY)
    }
}

class Food: NSObject, NSCoding {
    var name: String
    let FOOD_NAME_KEY = "FOOD_NAME_KEY"
    var calories: Int
    let CALORIE_KEY = "CALORIE_KEY"
    var id: Int
    let ID_KEY = "ID_KEY"
    init(name: String, calories: Int, id: Int) {
        self.name = name
        self.calories = calories
        self.id = id
        super.init()
    }
    
    required init(coder aDecoder: NSCoder) {
        self.name = aDecoder.decodeObjectForKey(FOOD_NAME_KEY) as String
        self.calories = aDecoder.decodeIntegerForKey(CALORIE_KEY)
        self.id = aDecoder.decodeIntegerForKey(ID_KEY)
    }
    
    func encodeWithCoder(aCoder: NSCoder) {
        aCoder.encodeObject(name, forKey: FOOD_NAME_KEY)
        aCoder.encodeInteger(calories, forKey: CALORIE_KEY)
        aCoder.encodeInteger(id, forKey: ID_KEY)
    }
}