//
//  MovieTableViewCell.swift
//  MovieBrowser
//
//  Created by Mark Struzinski on 1/15/18.
//  Copyright © 2018 BobStruz Software. All rights reserved.
//

import UIKit

class MovieTableViewCell: UITableViewCell {
    @IBOutlet weak var posterImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var releaseDateLabel: UILabel!
    @IBOutlet weak var averageRatingLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    
    func configure(with movie: Movie) {
        titleLabel.text = movie.title
        releaseDateLabel.text = movie.releaseDate
        averageRatingLabel.text = "Rating: \(String(format: "%.1F", movie.voteAverage))"
        descriptionLabel.text = movie.overview
    }
}
