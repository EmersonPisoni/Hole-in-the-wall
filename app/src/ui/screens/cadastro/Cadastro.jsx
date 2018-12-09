import React, { Component } from 'react'
import { UsuarioService } from './../../../service'
import { Redirect, Link } from 'react-router-dom'
import { Input, Button, Base } from './../../components'
import { EXCEPTIONS, CODIGOSEXCEPTIONS } from './../../../enums'

import './Cadastro.css'

export class Cadastro extends Component {

    constructor() {
        super()
        this.state = {
            id: '',
            nome: '',
            apelido: '',
            email: '',
            senha: '',
            deveMostarTelaCarregando: false,
            deveRedirecionarParaJogo: false,
            deveMostrarApelidoInvalido: false,
            deveMostrarEmailInvalido: false,
            deveMostrarErroCamposVazios: false
        }
    }

    componentDidMount() {
        document.title = "Hole In The Wall"

        this.usuarioService = new UsuarioService()
    }

    onSubmit = (event) => {
        event.preventDefault()

        this.usuarioService.cadastrar(this.state.nome, this.state.apelido, this.state.email, this.state.senha).then((response) => {
            this.usuarioService.login(this.state.email, this.state.senha).then((response) => {
                this.usuarioService.salvarToken(response.data.token)
                this.setState({
                    deveRedirecionarParaJogo: true
                })
            })
        })
            .catch((err) => {
                this.setState({
                    deveMostrarErroCamposVazios: !this.state.nome || !this.state.apelido || !this.state.email || !this.state.senha,
                    deveMostrarApelidoInvalido: err.response.data.codigo === CODIGOSEXCEPTIONS.APELIDO_JA_EXISTE,
                    deveMostrarEmailInvalido: err.response.data.codigo === CODIGOSEXCEPTIONS.EMAIL_JA_EXISTE
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
                <Base titulo="Cadastro" classeAdicional="cadastro" form={this.form()} />
        )
    }

    form = () => {
        return (
            <form className="cadastro-formulario" onSubmit={this.onSubmit}>
                <p className="cadastro-titulo"> Cadastro </p>
                <div className="cadastro-container-formulario">
                    <Input
                        placeholder="Nome"
                        inputType="text" name="nome"
                        onChange={this.onHandleChange}
                        value={this.state.nome} />
                    <Input
                        mensagemErro={this.state.deveMostrarApelidoInvalido && EXCEPTIONS.APELIDO_JA_EXISTE}
                        placeholder="Apelido"
                        inputType="text"
                        name="apelido"
                        onChange={this.onHandleChange}
                        value={this.state.apelido} />
                    <Input
                        mensagemErro={this.state.deveMostrarEmailInvalido && EXCEPTIONS.EMAIL_JA_EXISTE}
                        placeholder="email@email.com"
                        inputType="email"
                        name="email" onChange={this.onHandleChange}
                        value={this.state.email} />
                    <Input
                        mensagemErro={this.state.deveMostrarErroCamposVazios && EXCEPTIONS.CAMPO_VAZIO}
                        placeholder="Senha"
                        inputType="password"
                        name="senha"
                        onChange={this.onHandleChange}
                        value={this.state.senha} />
                </div>
                <div className="cadastro-botoes">
                    <Button message="ENVIAR" type="submit" classeCor="button-cor-vermelha" />
                    <Link to='/login'><Button message="VOLTAR" type="button" classeCor="button-cor-bege" /></Link>
                </div>
            </form>
        )
    }

}
