//
//  MovieList.swift
//  MovieBrowser
//
//  Created by Mark Struzinski on 11/8/17.
//  Copyright © 2017 BobStruz Software. All rights reserved.
//

import Foundation

struct MovieList: Codable {
    let movies: [Movie]
    
    enum CodingKeys: String, CodingKey {
        case movies = "results"
    }
    
    static func from(jsonListData: Data) -> MovieList? {
        let decoder = JSONDecoder()
        
        let movies = try? decoder.decode(MovieList.self, from: jsonListData)
        return movies
    }
}
