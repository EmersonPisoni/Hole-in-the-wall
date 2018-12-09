import React, { Component } from 'react'
import { Redirect, Link } from 'react-router-dom'
import { Input, Button, Base } from './../../components'
import { UsuarioService } from './../../../service'
import { EXCEPTIONS, CODIGOSEXCEPTIONS } from './../../../enums'

import './../cadastro/Cadastro.css'
import './Login.css'

export class Login extends Component {

    constructor() {
        super()

        this.state = {
            emailOuApelido: '',
            senha: '',
            deveMostarTelaCarregando: false,
            deveRedirecionarParaJogo: false,
            deveMostrarUsuarioOuSenhaInvalidos: false,
            deveMostrarErroCamposVazios: false
        }
    }

    componentDidMount() {
        document.title = "Hole In The Wall"

        this.usuarioService = new UsuarioService()
    }

    onSubmit = (event) => {
        event.preventDefault()

        this.usuarioService.login(this.state.emailOuApelido, this.state.senha).then((response) => {
            this.usuarioService.salvarToken(response.data.token)
            this.setState({
                deveRedirecionarParaJogo: true
            })
        })
            .catch((err) => {
                this.setState({
                    deveMostrarErroCamposVazios: !this.state.emailOuApelido || !this.state.senha,
                    deveMostrarUsuarioOuSenhaInvalidos: err.response.data.codigo === CODIGOSEXCEPTIONS.USUARIO_OU_SENHA_INVALIDOS
                })
            })
    }

    onHandleChange = (event) => {
        const target = event.target

        this.setState({
            [target.name]: target.value
        });
    }

    render() {
        return (
            this.state.deveRedirecionarParaJogo ? <Redirect to="/" /> :
                <Base titulo="Login" form={this.form()} />
        );
    }

    form = () => {
        return (
            <form className="cadastro-formulario" onSubmit={this.onSubmit}>
                <p className="cadastro-titulo"> Login </p>
                <div className="cadastro-container-formulario">
                    <Input
                        placeholder="Email ou apelido"
                        inputType="text"
                        name="emailOuApelido"
                        onChange={this.onHandleChange}
                        value={this.state.emailOuApelido} />
                    <Input
                        mensagemErro={
                            (this.state.deveMostrarUsuarioOuSenhaInvalidos && EXCEPTIONS.USUARIO_OU_SENHA_INVALIDOS) ||
                            (this.state.deveMostrarErroCamposVazios && EXCEPTIONS.CAMPO_VAZIO)
                        }
                        placeholder="Senha"
                        inputType="password"
                        name="senha"
                        onChange={this.onHandleChange}
                        value={this.state.senha} />
                    <div className="div-link-criar-conta">
                        <Link to="/cadastro" className="link-criar-conta">CRIAR CONTA</Link>
                    </div>
                </div>
                <div className="cadastro-botoes">
                    <Button message="ENTRAR" type="submit" classeCor="button-cor-vermelha"></Button>
                </div>
            </form>
        )
    }

}
