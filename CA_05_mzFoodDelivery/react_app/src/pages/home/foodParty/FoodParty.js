import React from "react";

export default class FoodParty extends React.Component {
    render() {
        return (
            <div className="food-party">
                food party
                <div className="carousel"
                     data-flickity='{ "freeScroll": true, "contain": true, "prevNextButtons": false, "pageDots": false }'>
                    <div className="carousel-cell"></div>
                    <div className="carousel-cell"></div>
                    <div className="carousel-cell"></div>
                    <div className="carousel-cell"></div>
                    <div className="carousel-cell"></div>
                </div>
            </div>
        );
    }
}