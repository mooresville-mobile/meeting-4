//
//  APIAction.swift
//  MovieBrowser
//
//  Created by Mark Struzinski on 11/8/17.
//  Copyright © 2017 BobStruz Software. All rights reserved.
//

import Foundation

enum ApiAction: String {
    case search
    case reviews
    case genres
}

func printMyEnum() {
    let action = ApiAction.genres
    let stringValue = action.rawValue
}
