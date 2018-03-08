//
//  Movie.swift
//  MovieBrowser
//
//  Created by Mark Struzinski on 11/7/17.
//  Copyright © 2017 BobStruz Software. All rights reserved.
//

import Foundation

struct Movie: Codable {
    let title: String
    let voteAverage: Double
    let overview: String
    let posterURLString: String?
    let releaseDate: String
    
    enum CodingKeys: String, CodingKey {
        case title
        case voteAverage = "vote_average"
        case overview
        case posterURLString = "poster_path"
        case releaseDate = "release_date"
    }
    
    static func from(jsonData: Data) -> Movie? {
        let decoder = JSONDecoder()
        let movie = try? decoder.decode(Movie.self, from: jsonData)
        return movie
    }
}

extension Movie: CustomStringConvertible {
    var description: String {
        let descriptionString = """
        ################\n
        Movie(
        title: \(title)
        posterURLString: \(posterURLString ?? "")
        releaseDate: \(releaseDate)
        ################\n\n
        """
        
        return descriptionString
    }
}
