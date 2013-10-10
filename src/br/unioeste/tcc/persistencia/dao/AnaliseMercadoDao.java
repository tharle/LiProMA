/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.tcc.persistencia.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unioeste.tcc.modelo.entidade.AnaliseMercado;
import br.unioeste.tcc.modelo.execao.DaoException;

/**
 *
 * @author Charlinho
 */
public class AnaliseMercadoDao extends PostgresDao {

    protected static final String NAME_ENTITY = "analise_mercado";
    //Colunas
    protected static final String COLUMN_ID = "id";
    protected static final String COLUMN_ESTRATEGIA_MERCADO = "estrategia_mercado";
    protected static final String COLUMN_NECESSIDADE_MERCADO = "necessidade_mercado";
    protected static final String COLUMN_CONCORRENCIA = "concorrencia";
    protected static final String COLUMN_TECNOLOGIA_DESENVOLVIMENTO = "tecnologia_desenvolvimento";
    protected static final String COLUMN_AMBIENTE_COMPUTACIONAL = "ambiente_computacional";
    protected static final String COLUMN_PERFIL_CLIENTE = "perfil_cliente";
    protected static final String COLUMN_NIVEL_HABILIDADE = "nivel_habilidade";
    protected static final String COLUMN_RESTRICAO_CULTURAL = "restricao_cultural";
    protected static final String COLUMN_TEMPO_ENTREGA = "tempo_entrega";
    protected static final String COLUMN_OBJETIVO_NEGOCIO = "objetivo_negocio";
    protected static final String COLUMN_OBJETIVO_REUSO = "objetivo_reuso";
    //Consultas
    protected static final String SQL_SELECT = "SELECT " + COLUMN_ID + ", "
            + COLUMN_ESTRATEGIA_MERCADO + ", " + COLUMN_NECESSIDADE_MERCADO + ", " + COLUMN_CONCORRENCIA + ", "
            + COLUMN_PERFIL_CLIENTE + ", " + COLUMN_TECNOLOGIA_DESENVOLVIMENTO + ", " + COLUMN_AMBIENTE_COMPUTACIONAL
            + ", " + COLUMN_NIVEL_HABILIDADE + ", " + COLUMN_RESTRICAO_CULTURAL
            + ", " + COLUMN_TEMPO_ENTREGA + ", " + COLUMN_OBJETIVO_NEGOCIO + ", "
            + COLUMN_OBJETIVO_REUSO
            + " FROM " + NAME_ENTITY;
    protected static final String SQL_MAX_ID = "SELECT MAX(" + COLUMN_ID + ") FROM " + NAME_ENTITY;
    //Inserção
    protected static final String SQL_INSERT = ""
            + "INSERT INTO "
            + NAME_ENTITY
            + "(" + COLUMN_ESTRATEGIA_MERCADO + ", " + COLUMN_NECESSIDADE_MERCADO + ", " + COLUMN_CONCORRENCIA + ", "
            + COLUMN_PERFIL_CLIENTE + ", " + COLUMN_TECNOLOGIA_DESENVOLVIMENTO + ", " + COLUMN_AMBIENTE_COMPUTACIONAL
            + ", " + COLUMN_NIVEL_HABILIDADE + ", " + COLUMN_RESTRICAO_CULTURAL + ", " + COLUMN_TEMPO_ENTREGA
            + ", " + COLUMN_OBJETIVO_NEGOCIO + ", " + COLUMN_OBJETIVO_REUSO + ", "
            + ")"
            + "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //Atualização
    protected static final String SQL_UPDATE = ""
            + "UPDATE " + NAME_ENTITY + " SET " + COLUMN_ESTRATEGIA_MERCADO + " = ?, "
            + COLUMN_NECESSIDADE_MERCADO + " = ?, " + COLUMN_CONCORRENCIA + " = ?, "
            + COLUMN_PERFIL_CLIENTE + " = ?," + COLUMN_TECNOLOGIA_DESENVOLVIMENTO + " = ?," + COLUMN_AMBIENTE_COMPUTACIONAL
            + " = ?," + COLUMN_NIVEL_HABILIDADE + " = ?," + COLUMN_RESTRICAO_CULTURAL
            + " = ?," + COLUMN_TEMPO_ENTREGA + " = ?," + COLUMN_OBJETIVO_NEGOCIO + " = ?,"
            + COLUMN_OBJETIVO_REUSO + " = ?,"
            + " = ? WHERE " + COLUMN_ID + " = ?";
    //Remoção
    protected static final String SQL_DELETE = "DELETE FROM " + NAME_ENTITY + " WHERE " + COLUMN_ID + " = ?";

    public AnaliseMercadoDao() {
    }

    //--------------------------------------------------------------------------
    // INSERÃ‡ÃƒO, REMOÃ‡ÃƒO E ALTERAÃ‡ÃƒO
    //--------------------------------------------------------------------------
    @SuppressWarnings("finally")
	public Long insert(AnaliseMercado entidadeBanco) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = SQL_INSERT;
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int paramCount = 1;
            ps.setString(paramCount++, entidadeBanco.getEstrategiaMercado());
            ps.setString(paramCount++, entidadeBanco.getNecessidadeMercado());
            ps.setString(paramCount++, entidadeBanco.getConcorrencia());
            ps.setString(paramCount++, entidadeBanco.getTecnologiaDesenvolvimento());
            ps.setString(paramCount++, entidadeBanco.getAmbienteComputacional());
            ps.setString(paramCount++, entidadeBanco.getPerfilCliente());
            ps.setString(paramCount++, entidadeBanco.getNivelHabilidade());
            ps.setString(paramCount++, entidadeBanco.getRestricaoCultural());
            ps.setString(paramCount++, entidadeBanco.getTempoEntrega());
            ps.setString(paramCount++, entidadeBanco.getObjetivoNegocio());
            ps.setString(paramCount++, entidadeBanco.getObjetivoReuso());
            

            ps.executeUpdate();
            log.trace("SQL: " + sql);
            con.commit();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new DaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new DaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.rollback();
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (Exception e) {
                logger.error("Exception: " + e.getMessage(), e);
                throw new DaoException("Exception: " + e.getMessage(), e);
            }
            return entidadeBanco.getId();
        }
    }

    public int update(Long pk, AnaliseMercado entidadeBanco) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;
        int paramCount = 1;

        try {
            String sql = SQL_UPDATE;
            con = getConnection();
            ps = con.prepareStatement(sql);

            //paremetros
            ps.setString(paramCount++, entidadeBanco.getEstrategiaMercado());
            ps.setString(paramCount++, entidadeBanco.getNecessidadeMercado());
            ps.setString(paramCount++, entidadeBanco.getConcorrencia());
            ps.setString(paramCount++, entidadeBanco.getTecnologiaDesenvolvimento());
            ps.setString(paramCount++, entidadeBanco.getAmbienteComputacional());
            ps.setString(paramCount++, entidadeBanco.getPerfilCliente());
            ps.setString(paramCount++, entidadeBanco.getNivelHabilidade());
            ps.setString(paramCount++, entidadeBanco.getRestricaoCultural());
            ps.setString(paramCount++, entidadeBanco.getTempoEntrega());
            ps.setString(paramCount++, entidadeBanco.getObjetivoNegocio());
            ps.setString(paramCount++, entidadeBanco.getObjetivoReuso());

            //Setando id a ser atualizado
            ps.setLong(paramCount++, pk);
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new DaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new DaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return numRows;
    }

    public int delete(Long pk) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;

        try {
            String sql = SQL_DELETE;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, pk);
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new DaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new DaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return numRows;
    }

    //--------------------------------------------------------------------------
    // CONSULTAS
    //--------------------------------------------------------------------------
    public List<AnaliseMercado> findAll() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT;
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0) {
                sql += " LIMIT " + limit;
            }
            if (offset != null && offset.intValue() > 0) {
                sql += "offset " + offset;
            }
            con = getConnection();
            ps = con.prepareStatement(sql);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new DaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new DaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public List<AnaliseMercado> findWhereCodigoEquals(Long pk) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE " + COLUMN_ID + " = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0) {
                sql += " LIMIT " + limit;
            }
            if (offset != null && offset.intValue() > 0) {
                sql += " OFFSET " + offset;
            }
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, pk);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new DaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new DaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public int countAll() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //--------------------------------------------------------------------------
    // MÃ‰TODOS COMPLEMETARES
    //--------------------------------------------------------------------------
    public void populateDto(AnaliseMercado entidadeBanco, ResultSet rs) throws SQLException {
        entidadeBanco.setId(rs.getLong(COLUMN_ID));
        entidadeBanco.setEstrategiaMercado(rs.getString(COLUMN_ESTRATEGIA_MERCADO));
        entidadeBanco.setPerfilCliente(rs.getString(COLUMN_PERFIL_CLIENTE));
        entidadeBanco.setNecessidadeMercado(rs.getString(COLUMN_NECESSIDADE_MERCADO));
        entidadeBanco.setConcorrencia(rs.getString(COLUMN_CONCORRENCIA));
        entidadeBanco.setTecnologiaDesenvolvimento(rs.getString(COLUMN_TECNOLOGIA_DESENVOLVIMENTO));
        entidadeBanco.setAmbienteComputacional(rs.getString(COLUMN_AMBIENTE_COMPUTACIONAL));
        entidadeBanco.setNivelHabilidade(rs.getString(COLUMN_NIVEL_HABILIDADE));
        entidadeBanco.setRestricaoCultural(rs.getString(COLUMN_RESTRICAO_CULTURAL));
        entidadeBanco.setTempoEntrega(rs.getString(COLUMN_TEMPO_ENTREGA));
        entidadeBanco.setObjetivoNegocio(rs.getString(COLUMN_OBJETIVO_NEGOCIO));
        entidadeBanco.setObjetivoReuso(rs.getString(COLUMN_OBJETIVO_REUSO));
    }

    public List<AnaliseMercado> fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList<AnaliseMercado> results = new ArrayList<>();
        while (rs.next()) {
            AnaliseMercado entidadeBanco = new AnaliseMercado();
            populateDto(entidadeBanco, rs);
            results.add(entidadeBanco);
        }
        return results;
    }
}
