--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0 (Debian 17.0-1.pgdg120+1)
-- Dumped by pg_dump version 17.0

-- Started on 2024-11-19 14:58:29

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3419 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 90547)
-- Name: arbre; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.arbre (
    id integer NOT NULL,
    date_de_plantation timestamp without time zone,
    age integer,
    champid integer
);


ALTER TABLE public.arbre OWNER TO root;

--
-- TOC entry 221 (class 1259 OID 90546)
-- Name: arbre_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.arbre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.arbre_id_seq OWNER TO root;

--
-- TOC entry 3420 (class 0 OID 0)
-- Dependencies: 221
-- Name: arbre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.arbre_id_seq OWNED BY public.arbre.id;


--
-- TOC entry 220 (class 1259 OID 90535)
-- Name: champ; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.champ (
    id integer NOT NULL,
    superficie character varying(128),
    fermeid integer
);


ALTER TABLE public.champ OWNER TO root;

--
-- TOC entry 219 (class 1259 OID 90534)
-- Name: champ_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.champ_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.champ_id_seq OWNER TO root;

--
-- TOC entry 3421 (class 0 OID 0)
-- Dependencies: 219
-- Name: champ_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.champ_id_seq OWNED BY public.champ.id;


--
-- TOC entry 226 (class 1259 OID 90566)
-- Name: client; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.client (
    id integer NOT NULL,
    nom character varying(255),
    pass_word character varying(255),
    email character varying(255)
);


ALTER TABLE public.client OWNER TO root;

--
-- TOC entry 225 (class 1259 OID 90565)
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.client_id_seq OWNER TO root;

--
-- TOC entry 3422 (class 0 OID 0)
-- Dependencies: 225
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;


--
-- TOC entry 218 (class 1259 OID 90526)
-- Name: ferme; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.ferme (
    id integer NOT NULL,
    nom character varying(255),
    superficie character varying(128),
    localisation character varying(255),
    date_de_creation timestamp without time zone
);


ALTER TABLE public.ferme OWNER TO root;

--
-- TOC entry 217 (class 1259 OID 90525)
-- Name: ferme_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.ferme_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ferme_id_seq OWNER TO root;

--
-- TOC entry 3423 (class 0 OID 0)
-- Dependencies: 217
-- Name: ferme_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.ferme_id_seq OWNED BY public.ferme.id;


--
-- TOC entry 224 (class 1259 OID 90559)
-- Name: recolte; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.recolte (
    id integer NOT NULL,
    recolte_date timestamp without time zone,
    quantite integer,
    arbreid integer
);


ALTER TABLE public.recolte OWNER TO root;

--
-- TOC entry 223 (class 1259 OID 90558)
-- Name: recolte_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.recolte_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.recolte_id_seq OWNER TO root;

--
-- TOC entry 3424 (class 0 OID 0)
-- Dependencies: 223
-- Name: recolte_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.recolte_id_seq OWNED BY public.recolte.id;


--
-- TOC entry 228 (class 1259 OID 90575)
-- Name: vente; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.vente (
    id integer NOT NULL,
    date timestamp without time zone,
    prix_unitaire double precision,
    recolteid integer,
    clientid integer
);


ALTER TABLE public.vente OWNER TO root;

--
-- TOC entry 227 (class 1259 OID 90574)
-- Name: vente_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.vente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vente_id_seq OWNER TO root;

--
-- TOC entry 3425 (class 0 OID 0)
-- Dependencies: 227
-- Name: vente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.vente_id_seq OWNED BY public.vente.id;


--
-- TOC entry 3237 (class 2604 OID 90550)
-- Name: arbre id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.arbre ALTER COLUMN id SET DEFAULT nextval('public.arbre_id_seq'::regclass);


--
-- TOC entry 3236 (class 2604 OID 90538)
-- Name: champ id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.champ ALTER COLUMN id SET DEFAULT nextval('public.champ_id_seq'::regclass);


--
-- TOC entry 3239 (class 2604 OID 90569)
-- Name: client id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);


--
-- TOC entry 3235 (class 2604 OID 90529)
-- Name: ferme id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.ferme ALTER COLUMN id SET DEFAULT nextval('public.ferme_id_seq'::regclass);


--
-- TOC entry 3238 (class 2604 OID 90562)
-- Name: recolte id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.recolte ALTER COLUMN id SET DEFAULT nextval('public.recolte_id_seq'::regclass);


--
-- TOC entry 3240 (class 2604 OID 90578)
-- Name: vente id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.vente ALTER COLUMN id SET DEFAULT nextval('public.vente_id_seq'::regclass);


--
-- TOC entry 3407 (class 0 OID 90547)
-- Dependencies: 222
-- Data for Name: arbre; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.arbre (id, date_de_plantation, age, champid) FROM stdin;
\.


--
-- TOC entry 3405 (class 0 OID 90535)
-- Dependencies: 220
-- Data for Name: champ; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.champ (id, superficie, fermeid) FROM stdin;
\.


--
-- TOC entry 3411 (class 0 OID 90566)
-- Dependencies: 226
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.client (id, nom, pass_word, email) FROM stdin;
\.


--
-- TOC entry 3403 (class 0 OID 90526)
-- Dependencies: 218
-- Data for Name: ferme; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.ferme (id, nom, superficie, localisation, date_de_creation) FROM stdin;
\.


--
-- TOC entry 3409 (class 0 OID 90559)
-- Dependencies: 224
-- Data for Name: recolte; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.recolte (id, recolte_date, quantite, arbreid) FROM stdin;
\.


--
-- TOC entry 3413 (class 0 OID 90575)
-- Dependencies: 228
-- Data for Name: vente; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.vente (id, date, prix_unitaire, recolteid, clientid) FROM stdin;
\.


--
-- TOC entry 3426 (class 0 OID 0)
-- Dependencies: 221
-- Name: arbre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.arbre_id_seq', 1, false);


--
-- TOC entry 3427 (class 0 OID 0)
-- Dependencies: 219
-- Name: champ_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.champ_id_seq', 1, false);


--
-- TOC entry 3428 (class 0 OID 0)
-- Dependencies: 225
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.client_id_seq', 1, false);


--
-- TOC entry 3429 (class 0 OID 0)
-- Dependencies: 217
-- Name: ferme_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.ferme_id_seq', 1, false);


--
-- TOC entry 3430 (class 0 OID 0)
-- Dependencies: 223
-- Name: recolte_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.recolte_id_seq', 1, false);


--
-- TOC entry 3431 (class 0 OID 0)
-- Dependencies: 227
-- Name: vente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.vente_id_seq', 1, false);


--
-- TOC entry 3246 (class 2606 OID 90552)
-- Name: arbre arbre_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.arbre
    ADD CONSTRAINT arbre_pkey PRIMARY KEY (id);


--
-- TOC entry 3244 (class 2606 OID 90540)
-- Name: champ champ_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.champ
    ADD CONSTRAINT champ_pkey PRIMARY KEY (id);


--
-- TOC entry 3250 (class 2606 OID 90573)
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- TOC entry 3242 (class 2606 OID 90533)
-- Name: ferme ferme_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.ferme
    ADD CONSTRAINT ferme_pkey PRIMARY KEY (id);


--
-- TOC entry 3248 (class 2606 OID 90564)
-- Name: recolte recolte_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.recolte
    ADD CONSTRAINT recolte_pkey PRIMARY KEY (id);


--
-- TOC entry 3252 (class 2606 OID 90580)
-- Name: vente vente_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.vente
    ADD CONSTRAINT vente_pkey PRIMARY KEY (id);


--
-- TOC entry 3254 (class 2606 OID 90553)
-- Name: arbre fk_arbre_champ; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.arbre
    ADD CONSTRAINT fk_arbre_champ FOREIGN KEY (champid) REFERENCES public.champ(id);


--
-- TOC entry 3253 (class 2606 OID 90541)
-- Name: champ fk_champ_ferme; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.champ
    ADD CONSTRAINT fk_champ_ferme FOREIGN KEY (fermeid) REFERENCES public.ferme(id);


--
-- TOC entry 3255 (class 2606 OID 90586)
-- Name: vente fk_vente_client; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.vente
    ADD CONSTRAINT fk_vente_client FOREIGN KEY (clientid) REFERENCES public.client(id);


--
-- TOC entry 3256 (class 2606 OID 90581)
-- Name: vente fk_vente_recolte; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.vente
    ADD CONSTRAINT fk_vente_recolte FOREIGN KEY (recolteid) REFERENCES public.recolte(id);


-- Completed on 2024-11-19 14:58:29

--
-- PostgreSQL database dump complete
--

