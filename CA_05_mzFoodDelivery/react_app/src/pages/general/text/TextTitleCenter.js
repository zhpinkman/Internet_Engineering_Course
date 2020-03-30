import * as React from "react";
import "./text.css";

export default class TextTitleCenter extends React.Component {
    render() {
        return (
            <div className="center-text">
                <span className="text-title">
                    {this.props.text}
                </span>
            </div>
        );
    }
}