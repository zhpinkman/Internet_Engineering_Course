import React from "react";
import TextTitleCenter from "../../general/text/TextTitleCenter";
import RemainingTime from "./RemainingTime";

export default class FoodParty extends React.Component {
    render() {
        return (
            <div className="food-party">
                <TextTitleCenter text="جشن غذا!"/>
                <RemainingTime remainingTime="21:48" />
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