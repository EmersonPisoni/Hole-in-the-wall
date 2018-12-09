import React, { Component } from 'react'

import './Button.css'

export class Button extends Component {

    render() {
        return (
            <button type={this.props.type} className={`button-jogo ${this.props.classeCor}`} onClick={this.props.onClick}>
                {this.props.message}
                {this.props.children}
            </button>
        );
    }

}
