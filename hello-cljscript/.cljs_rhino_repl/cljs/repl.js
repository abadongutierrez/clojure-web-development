// Compiled by ClojureScript 0.0-3211 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
cljs.repl.print_doc = (function cljs$repl$print_doc(m){
cljs.core.println.call(null,"-------------------------");

cljs.core.println.call(null,[cljs.core.str((function (){var temp__4126__auto__ = new cljs.core.Keyword(null,"ns","ns",441598760).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(temp__4126__auto__)){
var ns = temp__4126__auto__;
return [cljs.core.str(ns),cljs.core.str("/")].join('');
} else {
return null;
}
})()),cljs.core.str(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Protocol");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m))){
var seq__5825_5837 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__5826_5838 = null;
var count__5827_5839 = (0);
var i__5828_5840 = (0);
while(true){
if((i__5828_5840 < count__5827_5839)){
var f_5841 = cljs.core._nth.call(null,chunk__5826_5838,i__5828_5840);
cljs.core.println.call(null,"  ",f_5841);

var G__5842 = seq__5825_5837;
var G__5843 = chunk__5826_5838;
var G__5844 = count__5827_5839;
var G__5845 = (i__5828_5840 + (1));
seq__5825_5837 = G__5842;
chunk__5826_5838 = G__5843;
count__5827_5839 = G__5844;
i__5828_5840 = G__5845;
continue;
} else {
var temp__4126__auto___5846 = cljs.core.seq.call(null,seq__5825_5837);
if(temp__4126__auto___5846){
var seq__5825_5847__$1 = temp__4126__auto___5846;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__5825_5847__$1)){
var c__5498__auto___5848 = cljs.core.chunk_first.call(null,seq__5825_5847__$1);
var G__5849 = cljs.core.chunk_rest.call(null,seq__5825_5847__$1);
var G__5850 = c__5498__auto___5848;
var G__5851 = cljs.core.count.call(null,c__5498__auto___5848);
var G__5852 = (0);
seq__5825_5837 = G__5849;
chunk__5826_5838 = G__5850;
count__5827_5839 = G__5851;
i__5828_5840 = G__5852;
continue;
} else {
var f_5853 = cljs.core.first.call(null,seq__5825_5847__$1);
cljs.core.println.call(null,"  ",f_5853);

var G__5854 = cljs.core.next.call(null,seq__5825_5847__$1);
var G__5855 = null;
var G__5856 = (0);
var G__5857 = (0);
seq__5825_5837 = G__5854;
chunk__5826_5838 = G__5855;
count__5827_5839 = G__5856;
i__5828_5840 = G__5857;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
if(cljs.core.truth_((function (){var or__4713__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__4713__auto__)){
return or__4713__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m));
} else {
cljs.core.prn.call(null,cljs.core.second.call(null,new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m)));
}
} else {
}
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"special-form","special-form",-1326536374).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Special Form");

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.contains_QMARK_.call(null,m,new cljs.core.Keyword(null,"url","url",276297046))){
if(cljs.core.truth_(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))){
return cljs.core.println.call(null,[cljs.core.str("\n  Please see http://clojure.org/"),cljs.core.str(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))].join(''));
} else {
return null;
}
} else {
return cljs.core.println.call(null,[cljs.core.str("\n  Please see http://clojure.org/special_forms#"),cljs.core.str(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Macro");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"REPL Special Function");
} else {
}

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
var seq__5829 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__5830 = null;
var count__5831 = (0);
var i__5832 = (0);
while(true){
if((i__5832 < count__5831)){
var vec__5833 = cljs.core._nth.call(null,chunk__5830,i__5832);
var name = cljs.core.nth.call(null,vec__5833,(0),null);
var map__5834 = cljs.core.nth.call(null,vec__5833,(1),null);
var map__5834__$1 = ((cljs.core.seq_QMARK_.call(null,map__5834))?cljs.core.apply.call(null,cljs.core.hash_map,map__5834):map__5834);
var arglists = cljs.core.get.call(null,map__5834__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
var doc = cljs.core.get.call(null,map__5834__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__5858 = seq__5829;
var G__5859 = chunk__5830;
var G__5860 = count__5831;
var G__5861 = (i__5832 + (1));
seq__5829 = G__5858;
chunk__5830 = G__5859;
count__5831 = G__5860;
i__5832 = G__5861;
continue;
} else {
var temp__4126__auto__ = cljs.core.seq.call(null,seq__5829);
if(temp__4126__auto__){
var seq__5829__$1 = temp__4126__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__5829__$1)){
var c__5498__auto__ = cljs.core.chunk_first.call(null,seq__5829__$1);
var G__5862 = cljs.core.chunk_rest.call(null,seq__5829__$1);
var G__5863 = c__5498__auto__;
var G__5864 = cljs.core.count.call(null,c__5498__auto__);
var G__5865 = (0);
seq__5829 = G__5862;
chunk__5830 = G__5863;
count__5831 = G__5864;
i__5832 = G__5865;
continue;
} else {
var vec__5835 = cljs.core.first.call(null,seq__5829__$1);
var name = cljs.core.nth.call(null,vec__5835,(0),null);
var map__5836 = cljs.core.nth.call(null,vec__5835,(1),null);
var map__5836__$1 = ((cljs.core.seq_QMARK_.call(null,map__5836))?cljs.core.apply.call(null,cljs.core.hash_map,map__5836):map__5836);
var arglists = cljs.core.get.call(null,map__5836__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
var doc = cljs.core.get.call(null,map__5836__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name);

cljs.core.println.call(null," ",arglists);

if(cljs.core.truth_(doc)){
cljs.core.println.call(null," ",doc);
} else {
}

var G__5866 = cljs.core.next.call(null,seq__5829__$1);
var G__5867 = null;
var G__5868 = (0);
var G__5869 = (0);
seq__5829 = G__5866;
chunk__5830 = G__5867;
count__5831 = G__5868;
i__5832 = G__5869;
continue;
}
} else {
return null;
}
}
break;
}
} else {
return null;
}
}
});

//# sourceMappingURL=repl.js.map