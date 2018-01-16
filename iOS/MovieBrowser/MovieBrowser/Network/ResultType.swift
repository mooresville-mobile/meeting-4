//
//  ResultType.swift
//  MovieBrowser
//
//  Created by Mark Struzinski on 11/7/17.
//  Copyright © 2017 BobStruz Software. All rights reserved.
//

import Foundation

enum ResultType<T> {
    case success(T)
    case error(Error)
}
