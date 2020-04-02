import React from "react";
import 'flickity/dist/flickity.min.css';

import TextTitleCenter from "../../general/text/TextTitleCenter";
import RemainingTime from "./RemainingTime";
import "./foodParty.css";
import FoodPartyCard from "./foodPartyCard/FoodPartyCard";
import {FoodPartyService, FoodPartyRemainingTimeService} from "../../../services/FoodPartyService";
import Slider from "../../../utils/Slider";
import {secondsToMMSS} from "../../../utils/utils";


export default class FoodParty extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            partyFoods: null,
            remainingTime: 0
        };
        this.sliderRef = React.createRef();
        this.intervalTimer = null;
        this._isMounted = false;
    }


    componentDidMount() {
        this._isMounted = true;
        this.intervalTimer = setInterval(() => {
            this.timerFunc();
        }, 1000);
    }

    componentWillUnmount() {
        this._isMounted = false;
        clearInterval(this.intervalTimer);
    }

    timerFunc() {
        let oldTime = this.state.remainingTime;
        if (oldTime >= 1) {
            this.setState({
                remainingTime: oldTime - 1
            });
        } else {
            this._isMounted && this.setState({
                partyFoods: null
            });
            if(this.sliderRef != null)
                this._isMounted && this.sliderRef.current.refreshFlickity();
            this.getPartyFoods();
            this.getRemainingTime();
        }
    }

    async getPartyFoods() {
        let partyFoods = await FoodPartyService.getPartyFoods();
        this._isMounted && await this.setState({
            partyFoods: partyFoods
        });
        if(this.sliderRef.current != null)
            this._isMounted && this.sliderRef.current.refreshFlickity();
    }


    async getRemainingTime() {
        let remainingTime = await FoodPartyRemainingTimeService.getRemainingTime();
        this._isMounted && this.setState({
            remainingTime: remainingTime
        });
    }

    render() {
        return (
            <div className="food-party">
                <TextTitleCenter text="جشن غذا!"/>
                <RemainingTime remainingTime={secondsToMMSS(this.state.remainingTime)}/>

                <div className="carousel">
                    <Slider ref={this.sliderRef}
                            options={{
                                // autoPlay: 4000,
                                // pauseAutoPlayOnHover: true,
                                // wrapAround: true,
                                // fullscreen: true,
                                adaptiveHeight: true,

                                freeScroll: true,
                                contain: true,
                                prevNextButtons: false,
                                pageDots: false
                            }}
                    >
                        {this.foodPartyList()}
                    </Slider>
                </div>

            </div>

        );
    }

    foodPartyList() {
        if (this.state.partyFoods == null) {
            return (
                <div className="spinner-grow text-danger food-party-loading-box align-self-center" role="status">
                    <span className="sr-only">Loading...</span>
                </div>
            );
        } else {
            return this.state.partyFoods.map((partyFood, i) =>
                <FoodPartyCard partyFood={partyFood} key={"party"+i}/>
            );
        }
    }
}

